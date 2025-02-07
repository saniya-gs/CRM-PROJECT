package com.ourdept.crm_software.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.SocialMedia;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {

	boolean existsByEmployee_EmpId(long employeeId);

	Optional<SocialMedia> findByEmployee_EmpId(long employeeId);

}