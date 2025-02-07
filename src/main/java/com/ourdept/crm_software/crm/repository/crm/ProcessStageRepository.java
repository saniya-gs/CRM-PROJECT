package com.ourdept.crm_software.crm.repository.crm;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.crm.ProcessStage;

public interface ProcessStageRepository extends JpaRepository<ProcessStage, Long> {

	ProcessStage findBySequenceNumber(int i);
    Optional<ProcessStage> findBySequenceNumberAndDepartment_DepartmentId(Integer sequenceNumber, Long departmentId);

}
