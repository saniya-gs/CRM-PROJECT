package com.ourdept.crm_software.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Certification;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

	boolean existsByCertificationNameAndEmployee_EmpId(String certificationName, long employeeId);

	List<Certification> findAllByEmployee_EmpId(long employeeId);

}
