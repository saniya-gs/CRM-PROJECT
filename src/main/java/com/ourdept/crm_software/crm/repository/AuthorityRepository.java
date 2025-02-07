package com.ourdept.crm_software.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Authorities;

public interface AuthorityRepository extends JpaRepository<Authorities, Long> {

	boolean existsByName(String name);

	Authorities findByName(String roleName);

}

