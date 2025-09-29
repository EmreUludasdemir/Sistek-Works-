package com.sistek.sos.analysis_dashboard.services;

import com.sistek.sos.analysis_dashboard.dto.LineOverviewDto;
import com.sistek.sos.analysis_dashboard.repositories.LineOverviewRepository;
import com.sistek.sos.analysis_dashboard.repositories.projections.LineOverviewProjection;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.*;
import java.util.List;

@Service
public class LineOverviewService {
    private final LineOverviewRepository repo;

    public LineOverviewService(LineOverviewRepository repo) {
        this.repo = repo;
    }

    public List<LineOverviewDto> getOverview() {
        // Varsayılan: bugün 00:00 – şimdi
        ZoneId tz = ZoneId.systemDefault();
        List<LineOverviewProjection> rows = repo.findOverview();
        return rows.stream()
                .map(r -> new LineOverviewDto(r.getId(), r.getLineName(), r.getBarcodeCount(), r.getStatus()))
                .toList();
    }
}
