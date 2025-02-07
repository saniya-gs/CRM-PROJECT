package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.TenantConfigInfo;

public interface TenantConfigInfoRepository extends JpaRepository<TenantConfigInfo, Integer> {


	boolean existsBySubDomainUrl(String subdomainName);

	 @Query("SELECT t.tenantId FROM TenantConfigInfo t")
	    List<String> findAllTenantIds();

}
