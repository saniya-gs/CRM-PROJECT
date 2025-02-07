package com.ourdept.crm_software.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.RoleDetails;
import com.ourdept.crm_software.crm.domain.enums.Status;

public interface RoleDetailsRepository extends JpaRepository<RoleDetails, Long> {

	Optional<RoleDetails> findByRoleName(String roleName);

	List<RoleDetails> findByStatus(Status enabled);

	boolean existsByRoleName(String roleName);


}
