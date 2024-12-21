package com.web.common.repos;

import com.web.common.entities.ServiceAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAuditRepository extends JpaRepository<ServiceAudit, String> {}
