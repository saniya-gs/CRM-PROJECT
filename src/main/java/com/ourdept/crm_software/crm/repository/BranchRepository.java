package com.ourdept.crm_software.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.company.tenant_registration.domain.enums.Status;
import com.ourdept.crm_software.crm.domain.entities.core.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

	Optional<Branch> findByBranchName(String branchName);

	List<Branch> findByStatus(Status enabled);

	List<Branch> findByStatusAndDepartmentsNotNull(Status enabled);

}
