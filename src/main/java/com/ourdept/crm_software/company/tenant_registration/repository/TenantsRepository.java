package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.Tenants;

@Repository
public interface TenantsRepository extends JpaRepository<Tenants, Integer> {

	Optional<Tenants> findByUsername(String username);

	Optional<Tenants> findByEmail(String email);


	

}