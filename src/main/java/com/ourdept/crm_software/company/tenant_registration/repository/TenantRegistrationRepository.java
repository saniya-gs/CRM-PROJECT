package com.ourdept.crm_software.company.tenant_registration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.TenantRegistration;



@Repository
public interface TenantRegistrationRepository extends JpaRepository<TenantRegistration, Integer> {

	TenantRegistration findByUsername(String username);
}
