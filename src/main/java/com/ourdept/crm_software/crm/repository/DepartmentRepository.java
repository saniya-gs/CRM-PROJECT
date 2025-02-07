package com.ourdept.crm_software.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ourdept.crm_software.crm.domain.entities.core.Department;
import com.ourdept.crm_software.crm.domain.enums.Status;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	

	Optional<Department> findByDepartmentName(String deptName);

	List<Department> findByStatus(Status enabled);

	boolean existsByDepartmentName(String deptName);



    @Query("SELECT d FROM Department d WHERE d.workingDays.workingDayId = :workingDaysId")
    List<Department> findByWorkingDaysId(@Param("workingDaysId") long workingDaysId);
}

