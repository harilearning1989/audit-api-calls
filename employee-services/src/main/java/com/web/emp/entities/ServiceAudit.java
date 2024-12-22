package com.web.emp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "SERVICE_AUDIT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String hostname;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long duration; // Duration in milliseconds
    private String requestMethod;
    private String requestBody;
    private String responseBody;
    private int responseStatus;
    @ElementCollection
    private Map<String, String> headers;
    private boolean isExternalApi;

    private String requestUrl;
    private String requestHeaders;
    private LocalDateTime requestStartTime;
    private LocalDateTime requestEndTime;
    private int responseStatusCode;

    @OneToMany(mappedBy = "serviceAudit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientAudit> clientAudits = new ArrayList<>();

    public void addClientAudit(ClientAudit clientAudit) {
        clientAudits.add(clientAudit);
        clientAudit.setServiceAudit(this);
    }

}
