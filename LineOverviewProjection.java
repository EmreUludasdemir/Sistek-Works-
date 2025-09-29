package com.sistek.sos.analysis_dashboard.repositories.projections;

public interface LineOverviewProjection {
    Long getId();
    String getLineName();
    Integer getBarcodeCount();
    String getStatus();
}
