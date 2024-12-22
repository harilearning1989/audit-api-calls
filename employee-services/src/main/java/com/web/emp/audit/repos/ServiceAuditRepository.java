package com.web.emp.audit.repos;

import com.web.emp.entities.ServiceAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAuditRepository extends JpaRepository<ServiceAudit, Long> {
}
