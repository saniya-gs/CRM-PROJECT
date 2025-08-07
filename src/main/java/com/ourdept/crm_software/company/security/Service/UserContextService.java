package com.ourdept.crm_software.company.security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ourdept.crm_software.ExceptionHandler.CustomException;
import com.ourdept.crm_software.crm.domain.entities.core.Employee;
import com.ourdept.crm_software.crm.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserContextService {

	@Autowired
	EmployeeRepository employeeRepository;

	// Get the currently authenticated user's details
	public Employee getCurrentUser() {
		Employee employee;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated() || authentication.getName() == null) {
			throw new CustomException("User is not authenticated");

		}
		log.info("trying to get the employee from: "+ authentication.getName());
		Optional<Employee> optionalemployee = employeeRepository.findByCompanyEmail(authentication.getName());
		if (optionalemployee.isEmpty()) {
			throw new CustomException("User is not Found for the email: "+authentication.getName());

		}
		employee = optionalemployee.get();

		return employee; // Assuming EmployeeDetails implements UserDetails
	}

	// Get the current user's empId
	public long getEmpId() {
		return getCurrentUser().getEmpId();
	}

	// Get the current user's HRId
	public long getHRId() {
		Employee employeeDetails = getCurrentUser();

		Employee HR = employeeDetails.getHr() == null ? new Employee() : employeeDetails.getHr(); // Assuming
																													// `HRId`
																													// i
		return HR.getEmpId();
	}

	// Get the current user's ManagerId
	public long getManagerId() {
		Employee employeeDetails = getCurrentUser();

		Employee manager = employeeDetails.getManager() == null ? new Employee() : employeeDetails.getManager(); // Assuming
																													// `managerId`
																													// is
																													// part
																													// of
																													// the
																													// EmployeeDetails
																													// object

		return manager.getEmpId();

	}
	
	
	// Get the currently authenticated user's details
		public Employee getEmployeeById(long employeeId) {
			Employee employee;
			
			Optional<Employee> optionalemployee = employeeRepository.findById(employeeId);
			if (optionalemployee.isEmpty()) {
				throw new CustomException("User is not Found for the email: "+employeeId);

			}
			employee = optionalemployee.get();

			return employee; // Assuming EmployeeDetails implements UserDetails
		}

}
