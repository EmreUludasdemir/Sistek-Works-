package com.sistek.sos.analysis_dashboard.controllers;

import com.sistek.sos.analysis_dashboard.entities.PlcInfo;
import com.sistek.sos.analysis_dashboard.services.PlcInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/plc")
public class PlcInfoController {
    @Autowired
    private PlcInfoService plcInfoService;

    @GetMapping("/info")
    public Map<String, Object> getPlcInfo() {
        PlcInfo plc = plcInfoService.getLatestActivePlc();
        Map<String, Object> result = new HashMap<>();
        if (plc != null) {
            result.put("plcIp", plc.getPlcIp());
            // Status mapping: 'ACTIVE' -> 'Active', 'INACTIVE' -> 'Inactive'
            String status = plc.getStatus();
            if ("ACTIVE".equalsIgnoreCase(status)) {
                result.put("status", "Active");
            } else {
                result.put("status", "Inactive");
            }
            result.put("updated_at", plc.getLastStatusDate());
        } else {
            result.put("plcIp", "-");
            result.put("status", "Inactive");
            result.put("updated_at", null);
        }
        return result;
    }
}
