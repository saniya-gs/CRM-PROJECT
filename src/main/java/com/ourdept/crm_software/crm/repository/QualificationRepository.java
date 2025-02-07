package com.ourdept.crm_software.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {

	List<Qualification> findAllByEmployee_EmpId(long employeeId);

}