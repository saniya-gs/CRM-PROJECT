package com.ourdept.crm_software.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.OfficeTiming;

public interface OfficeTimingRepository extends JpaRepository<OfficeTiming, Long> {

	boolean existsByShiftName(String shiftName);

	boolean existsByShiftNameAndOfficeTimingIdNot(String shiftName, long officeTimingId);

}