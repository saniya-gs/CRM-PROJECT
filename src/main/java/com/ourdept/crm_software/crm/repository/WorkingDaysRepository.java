package com.ourdept.crm_software.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.crm.domain.entities.core.WorkingDays;

@Repository
public interface WorkingDaysRepository extends JpaRepository<WorkingDays, Long> {

    boolean existsByBranchesOrDepartments(List<Long> branchIds, List<Long> departmentIds);

    @Query("SELECT COUNT(wd) > 0 FROM WorkingDays wd " +
    	       "LEFT JOIN wd.branches b " +
    	       "LEFT JOIN wd.departments d " +
    	       "WHERE b.branchId IN :branchIds AND d.departmentId IN :departmentIds")
    	boolean existsByBranchesAndDepartments(@Param("branchIds") List<Long> branchIds, 
    	                                       @Param("departmentIds") List<Long> departmentIds);


	List<WorkingDays> findByBranchesAndDepartments(List<Long> branchIds, List<Long> departmentIds);

	boolean existsByWorkingTypeName(String workingTypeName);
}
