package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.PurchaseDetails;
import com.ourdept.crm_software.company.tenant_registration.domain.entity.Tenants;

public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, Integer>{

	Optional<Tenants> findByTenants(Optional<Tenants> existingTenant);

	PurchaseDetails findByTenants(Tenants currentTenant);

}
