package com.ourdept.crm_software.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.CompanyAddress;

public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, Long>{

	Optional<CompanyAddress> findByCompany_CompanyId(long companyId);

}
