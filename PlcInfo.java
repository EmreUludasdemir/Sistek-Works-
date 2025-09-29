package com.sistek.sos.analysis_dashboard.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plc_info")
public class PlcInfo {
    @Id
    @Column(name = "plc_id")
    private String plcId;

    @Column(name = "plc_ip")
    private String plcIp;

    @Column(name = "status")
    private String status;

    @Column(name = "last_status_date")
    private LocalDateTime lastStatusDate;

    // Getters and setters
    public String getPlcId() { return plcId; }
    public void setPlcId(String plcId) { this.plcId = plcId; }
    public String getPlcIp() { return plcIp; }
    public void setPlcIp(String plcIp) { this.plcIp = plcIp; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getLastStatusDate() { return lastStatusDate; }
    public void setLastStatusDate(LocalDateTime lastStatusDate) { this.lastStatusDate = lastStatusDate; }
}
