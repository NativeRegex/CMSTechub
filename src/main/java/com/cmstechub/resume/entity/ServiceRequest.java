package com.cmstechub.resume.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientEmail;
    private String clientPhone;
    
    @Column(length = 2000)
    private String projectDetails;
    
    private String status = "PENDING"; // PENDING, IN_PROGRESS, COMPLETED
    private LocalDateTime requestDate = LocalDateTime.now();

    // Link to the Service table
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public String getClientPhone() { return clientPhone; }
    public void setClientPhone(String clientPhone) { this.clientPhone = clientPhone; }

    public String getProjectDetails() { return projectDetails; }
    public void setProjectDetails(String projectDetails) { this.projectDetails = projectDetails; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }

    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }
}
