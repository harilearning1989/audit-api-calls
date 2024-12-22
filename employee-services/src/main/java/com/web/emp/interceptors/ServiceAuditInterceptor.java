package com.web.emp.interceptors;

import com.web.emp.audit.repos.ServiceAuditRepository;
import com.web.emp.context.AuditContext;
import com.web.emp.entities.ServiceAudit;
import com.web.emp.wrappers.RequestWrapper;
import com.web.emp.wrappers.ResponseWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class ServiceAuditInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAuditInterceptor.class);

    private ThreadLocal<ServiceAudit> auditThreadLocal = new ThreadLocal<>();

    @Autowired
    private ServiceAuditRepository serviceAuditRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServiceAudit audit = new ServiceAudit();
        audit.setUrl(request.getRequestURL().toString());
        audit.setHostname(request.getServerName());
        audit.setStartTime(LocalDateTime.now());
        audit.setHeaders(Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, request::getHeader)));
        audit.setRequestMethod(request.getMethod());
        // Capture the request body
        if (request instanceof RequestWrapper) {
            audit.setRequestBody(((RequestWrapper) request).getRequestBody());
        }
        auditThreadLocal.set(audit);

        AuditContext.setServiceAudit(audit); // Store for client audit linking
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ServiceAudit audit = auditThreadLocal.get();
        ServiceAudit serviceAudit = AuditContext.getServiceAudit();
        if (audit != null) {
            audit.setEndTime(LocalDateTime.now());
            audit.setDuration(Duration.between(audit.getStartTime(), audit.getEndTime()).toMillis());
            audit.setResponseStatus(response.getStatus());
            // Capture the response body
            if (response instanceof ResponseWrapper) {
                audit.setResponseBody(((ResponseWrapper) response).getResponseBody());
            }
            LOGGER.info("ServiceAudit Data Details: {}", audit);

            response.getWriter().write(audit.getResponseBody());
            response.getWriter().flush();
            //serviceAuditRepository.save(audit);

            if (serviceAudit != null) {
                serviceAudit.setRequestEndTime(LocalDateTime.now());
                serviceAudit.setResponseStatusCode(response.getStatus());
                serviceAudit.setDuration(
                        java.time.Duration.between(serviceAudit.getRequestStartTime(), serviceAudit.getRequestEndTime()).toMillis()
                );
                //serviceAuditRepository.save(serviceAudit);
                AuditContext.clear(); // Clean up ThreadLocal
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle=========");
    }
}
