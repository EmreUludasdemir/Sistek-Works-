package com.sistek.sos.analysis_dashboard.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final JdbcTemplate jdbc;
    public StatsController(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    // Günlük toplam (timezone-safe; DB bağımsız aralık filtresi)
    @GetMapping("/daily-total")
    public Map<String, Object> dailyTotal(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        var start = date.atStartOfDay();
        var end   = start.plusDays(1);

        Long total = jdbc.queryForObject(
                "SELECT COUNT(*) FROM barcode_data WHERE cre_date >= ? AND cre_date < ?",
                Long.class,
                Timestamp.valueOf(start),
                Timestamp.valueOf(end)
        );
        return Map.of("date", date.toString(), "total", total == null ? 0 : total);
    }

    // Günlük her line için adet (DB bağımsız aralık filtresi)
    @GetMapping("/daily-per-line")
    public List<Map<String, Object>> dailyPerLine(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        var start = date.atStartOfDay();
        var end   = start.plusDays(1);

        String sql =
                "SELECT line_id, line_code, COUNT(*) AS cnt " +
                        "FROM barcode_data " +
                        "WHERE cre_date >= ? AND cre_date < ? " +
                        "GROUP BY line_id, line_code " +
                        "ORDER BY line_code";

        return jdbc.query(sql, (rs, i) -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("lineId", rs.getString("line_id"));     // line_id varchar
            m.put("lineCode", rs.getString("line_code")); // görsel isim
            m.put("count", rs.getLong("cnt"));
            return m;
        }, Timestamp.valueOf(start), Timestamp.valueOf(end));
    }

    // Seçilen gün için saatlik toplam/ortalama (0-23; boş saatler 0) — DB bağımsız
    @GetMapping("/hourly")
    public List<Map<String, Object>> hourly(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        var start = date.atStartOfDay();
        var end   = start.plusDays(1);

        // SQL Server kullanıyorsan EXTRACT yerine DATEPART(HOUR, cre_date) kullan:
        // String sql =
        //     "SELECT DATEPART(HOUR, cre_date) AS hr, COUNT(*) AS cnt " +
        //     "FROM barcode_data WHERE cre_date >= ? AND cre_date < ? " +
        //     "GROUP BY DATEPART(HOUR, cre_date) ORDER BY hr";

        String sql =
                "SELECT EXTRACT(HOUR FROM cre_date) AS hr, COUNT(*) AS cnt " +
                        "FROM barcode_data " +
                        "WHERE cre_date >= ? AND cre_date < ? " +
                        "GROUP BY EXTRACT(HOUR FROM cre_date) " +
                        "ORDER BY hr";

        // Ambiguity olmaması için ResultSetExtractor imzası kullanıldı
        Map<Integer, Long> byHour = jdbc.query(
                sql,
                new Object[]{ Timestamp.valueOf(start), Timestamp.valueOf(end) },
                (ResultSetExtractor<Map<Integer, Long>>) rs -> {
                    Map<Integer, Long> m = new HashMap<>();
                    while (rs.next()) {
                        m.put(rs.getInt("hr"), rs.getLong("cnt"));
                    }
                    return m;
                }
        );

        // 0..23 tüm saatleri doldur
        List<Map<String, Object>> out = new ArrayList<>(24);
        for (int h = 0; h < 24; h++) {
            long cnt = byHour.getOrDefault(h, 0L);
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("hour", h);
            row.put("count", cnt);               // tek saat penceresinde "ortalama" = toplam
            row.put("avgPerMinute", cnt / 60.0); // opsiyonel bilgi
            out.add(row);
        }
        return out;
    }
}
