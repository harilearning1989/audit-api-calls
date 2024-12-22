package com.web.emp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "CLIENT_AUDIT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalApiUrl;
    private String requestMethod;
    private String requestHeaders;
    private LocalDateTime requestStartTime;
    private LocalDateTime requestEndTime;
    private String responseBody;
    private int responseStatusCode;
    private long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_audit_id")
    private ServiceAudit serviceAudit;
}

