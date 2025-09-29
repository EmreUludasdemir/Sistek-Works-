package com.sistek.sos.analysis_dashboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/ui/lines")
public class LinesOverviewController {
    @GetMapping("/overview")
    public String getLinesOverview(Model model,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return "dashboard"; // points to dashboard.html
    }
}
