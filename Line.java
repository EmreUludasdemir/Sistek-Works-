package com.sistek.sos.analysis_dashboard.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "line_info")
public class Line {
    @Id
    @Column(name = "line_id")
    private String id;

    @Column(name = "line_name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "last_status_date")
    private LocalDateTime lastStatusDate;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getLastStatusDate() { return lastStatusDate; }
    public void setLastStatusDate(LocalDateTime lastStatusDate) { this.lastStatusDate = lastStatusDate; }
}
