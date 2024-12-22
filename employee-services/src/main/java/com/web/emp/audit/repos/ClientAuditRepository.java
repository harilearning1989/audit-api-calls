package com.web.emp.audit.repos;

import com.web.utils.entities.ClientAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAuditRepository extends JpaRepository<ClientAudit, Long> {
}
