package com.ourdept.crm_software.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Holidays;

public interface HolidaysRepository extends JpaRepository<Holidays, Long> {

	Optional<Holidays> findByHolidayName(String holidayName);


	List<Holidays> findByBranches_BranchId(long branchId);

}