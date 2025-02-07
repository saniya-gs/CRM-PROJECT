package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.MasterEmployees;

public interface MasterEmployeesRepository extends JpaRepository<MasterEmployees, Integer>{

	Optional<MasterEmployees> findByEmail(String username);

}
