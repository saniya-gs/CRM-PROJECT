package com.ourdept.crm_software.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

	List<Experience> findAllByEmployee_EmpId(long employeeId);

}
