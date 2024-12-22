package com.web.emp.context;

import com.web.emp.entities.ServiceAudit;

public class AuditContext {

    private static final ThreadLocal<ServiceAudit> serviceAuditHolder = new ThreadLocal<>();

    public static void setServiceAudit(ServiceAudit serviceAudit) {
        serviceAuditHolder.set(serviceAudit);
    }

    public static ServiceAudit getServiceAudit() {
        return serviceAuditHolder.get();
    }

    public static void clear() {
        serviceAuditHolder.remove();
    }
}

