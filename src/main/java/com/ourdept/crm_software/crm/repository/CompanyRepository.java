package com.ourdept.crm_software.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByCompanyName(String string);

}
