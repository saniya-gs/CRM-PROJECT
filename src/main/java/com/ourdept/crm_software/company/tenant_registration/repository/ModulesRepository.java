package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.Modules;
import com.ourdept.crm_software.company.tenant_registration.domain.enums.Status;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Integer>{
	

	Optional<Modules> findByModuleCode(String moduleCode);

	 // Check if a module exists by module name
    Optional<Modules> findByModuleName(String moduleName);

    // Check if a module exists by module name except the current module (for updates)
    Optional<Modules> findByModuleNameAndModuleIdNot(String moduleName, int moduleId);

    // Fetch all modules with their features eagerly
    List<Modules> findAll();

    // Fetch module names and IDs only
    List<Modules> findByStatus(Status status);


}
