package com.web.utils.repos;

import com.web.utils.entities.ServiceAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAuditRepository extends JpaRepository<ServiceAudit, String> {}
