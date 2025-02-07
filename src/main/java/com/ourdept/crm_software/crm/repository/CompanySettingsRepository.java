package com.ourdept.crm_software.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Company;
import com.ourdept.crm_software.crm.domain.entities.core.CompanySettings;

public interface CompanySettingsRepository extends JpaRepository<CompanySettings, Integer>{

	Optional<CompanySettings> findByCompany_CompanyId(long companyId);

	Optional<CompanySettings> findByCompany(Company company);

}
