package com.sistek.sos.analysis_dashboard.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "line_info")
public class LineInfo {
    @Id
    @Column(name = "line_id")
    private String lineId;

    @Column(name = "line_name")
    private String lineName;

    @Column(name = "status")
    private String status;

    @Column(name = "last_status_date")
    private LocalDateTime lastStatusDate;

    // Getters and setters
    public String getLineId() { return lineId; }
    public void setLineId(String lineId) { this.lineId = lineId; }
    public String getLineName() { return lineName; }
    public void setLineName(String lineName) { this.lineName = lineName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getLastStatusDate() { return lastStatusDate; }
    public void setLastStatusDate(LocalDateTime lastStatusDate) { this.lastStatusDate = lastStatusDate; }
}
