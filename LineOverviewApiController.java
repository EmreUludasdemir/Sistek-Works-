package com.sistek.sos.analysis_dashboard.api;

import com.sistek.sos.analysis_dashboard.dto.LineOverviewDto;
import com.sistek.sos.analysis_dashboard.services.LineOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/lines")
public class LineOverviewApiController {
    private final LineOverviewService lineOverviewService;

    @Autowired
    public LineOverviewApiController(LineOverviewService lineOverviewService) {
        this.lineOverviewService = lineOverviewService;
    }

    @GetMapping("/overview")
    public List<LineOverviewDto> getOverview(
    ) {
        return lineOverviewService.getOverview();
    }
}
