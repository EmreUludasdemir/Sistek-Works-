package com.sistek.sos.analysis_dashboard.repositories;

import com.sistek.sos.analysis_dashboard.domain.Line;
import com.sistek.sos.analysis_dashboard.repositories.projections.LineOverviewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LineOverviewRepository extends JpaRepository<Line, Long> {
    @Query(value = """
        WITH counts AS (
          SELECT b.line_id, COUNT(*) AS barcode_count
          FROM public.barcode_data b
          GROUP BY b.line_id
        ),
        last_seen AS (
          SELECT b.line_id, MAX(b.cre_date) AS last_ts
          FROM public.barcode_data b
          GROUP BY b.line_id
        )
        SELECT
          l.line_id AS id,
          l.line_name AS lineName,
          COALESCE(c.barcode_count, 0) AS barcodeCount,
          status
        FROM public.line_info l
        LEFT JOIN counts c     ON c.line_id = l.line_id
        LEFT JOIN last_seen ls ON ls.line_id = l.line_id
        ORDER BY l.line_name
        """, nativeQuery = true)
    List<LineOverviewProjection> findOverview();
}
