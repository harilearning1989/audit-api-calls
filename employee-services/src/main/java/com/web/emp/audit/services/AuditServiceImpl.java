package com.web.emp.audit.services;

import com.web.emp.audit.repos.ClientAuditRepository;
import com.web.emp.audit.repos.ServiceAuditRepository;
import com.web.emp.entities.ServiceAudit;
import com.web.utils.entities.ClientAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuditServiceImpl {

    @Autowired
    private ServiceAuditRepository serviceAuditRepository;

    @Autowired
    private ClientAuditRepository clientAuditRepository;

    @Transactional
    public ServiceAudit logServiceAudit(String url, String method, LocalDateTime startTime, LocalDateTime endTime,
                                        Long duration, String requestBody, String responseBody, int statusCode, String headers) {
        ServiceAudit audit = new ServiceAudit();
        audit.setUrl(url);
        //audit.setMethod(method);
        audit.setStartTime(startTime);
        audit.setEndTime(endTime);
        audit.setDuration(duration);
        audit.setRequestBody(requestBody);
        audit.setResponseBody(responseBody);
        //audit.setStatusCode(statusCode);
        //audit.setHeaders(headers);
        return serviceAuditRepository.save(audit);
    }

    @Transactional
    public ClientAudit logClientAudit(ServiceAudit serviceAudit, String url, String method, LocalDateTime startTime,
                                      LocalDateTime endTime, Long duration, String requestBody, String responseBody,
                                      int statusCode, String headers) {
        ClientAudit clientAudit = new ClientAudit();
        //clientAudit.setServiceAudit(serviceAudit);
        /*clientAudit.setUrl(url);
        clientAudit.setMethod(method);
        clientAudit.setStartTime(startTime);
        clientAudit.setEndTime(endTime);
        clientAudit.setDuration(duration);
        clientAudit.setRequestBody(requestBody);
        clientAudit.setResponseBody(responseBody);
        clientAudit.setStatusCode(statusCode);
        clientAudit.setHeaders(headers);*/
        return clientAuditRepository.save(clientAudit);
    }
}
