package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.MasterUsers;

public interface MasterUserRepository extends JpaRepository<MasterUsers, Integer>{

	Optional<MasterUsers> findByEmail(String email);


	Optional<MasterUsers> findByUserName(String username);

}
