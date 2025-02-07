package com.ourdept.crm_software.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.crm.domain.entities.core.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String username);

	  @Query("SELECT e.empId FROM Employee e WHERE e.manager.empId = :managerId")
	    List<Long> findEmployeeIdsByManagerId(long managerId);
	  

	  @Query("SELECT e FROM Employee e WHERE e.manager.empId = :managerId")
	    List<Employee> findEmployeeByManagerId(long managerId);


	boolean existsByEmail(String email);

	boolean existsByCompanyEmailOrMobileNo(String companyEmail, long mobileNo);

	boolean existsByCompanyEmailOrMobileNoAndEmpIdNot(String companyEmail, long mobileNo, long empId);

	Optional<Employee> findByEmailOrMobileNo(String email, long mobileNo);

	Optional<Employee> findByCompanyEmail(String username);

	boolean existsByCompanyEmail(String username);

//	boolean existsByUsernameAndTenantId(String username, String tenantID);
}
