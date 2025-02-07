package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.PurchaseRequest;
import com.ourdept.crm_software.company.tenant_registration.domain.entity.Tenants;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Integer>{


	Optional<PurchaseRequest> findByTenants(Tenants currentTenant);

	

}
