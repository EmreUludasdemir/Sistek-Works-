package com.sistek.sos.analysis_dashboard.dto;

public record LineOverviewDto(
    Long id,
    String lineName,
    Integer barcodeCount,
    String status
) {}
