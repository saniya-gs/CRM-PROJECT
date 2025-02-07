//
//package com.hr_labs.our_dept_com.hrm.SampleData.Config;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.hr_labs.our_dept_com.hrm.domain.entities.Core.Authorities;
//import com.hr_labs.our_dept_com.hrm.domain.entities.Core.Company;
//import com.hr_labs.our_dept_com.hrm.domain.entities.Core.CompanySettings;
//import com.hr_labs.our_dept_com.hrm.domain.entities.Core.Department;
//import com.hr_labs.our_dept_com.hrm.domain.entities.Core.Employee;
//import com.hr_labs.our_dept_com.hrm.domain.entities.Core.RoleDetails;
//import com.hr_labs.our_dept_com.hrm.domain.entities.Core.SocialMedia;
//import com.hr_labs.our_dept_com.hrm.domain.enums.BloodGroup;
//import com.hr_labs.our_dept_com.hrm.domain.enums.EmployementType;
//import com.hr_labs.our_dept_com.hrm.domain.enums.Gender;
//import com.hr_labs.our_dept_com.hrm.domain.enums.MaritalStatus;
//import com.hr_labs.our_dept_com.hrm.domain.enums.Status;
//import com.hr_labs.our_dept_com.hrm.repository.AuthorityRepository;
//import com.hr_labs.our_dept_com.hrm.repository.CompanyRepository;
//import com.hr_labs.our_dept_com.hrm.repository.CompanySettingsRepository;
//import com.hr_labs.our_dept_com.hrm.repository.DepartmentRepository;
//import com.hr_labs.our_dept_com.hrm.repository.EmployeeRepository;
//import com.hr_labs.our_dept_com.hrm.repository.RoleDetailsRepository;
//import com.hr_labs.our_dept_com.hrm.repository.SocialMediaRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@RequiredArgsConstructor
//public class StartupDataLoader {
//
//	private static final Logger logger = LoggerFactory.getLogger(StartupDataLoader.class);
//
//	private final EmployeeRepository employeeRepository;
//	private final AuthorityRepository authoritiesRepository;
//	private final CompanyRepository companyRepository;
//	private final CompanySettingsRepository companySettingsRepository;
//	private final PasswordEncoder passwordEncoder;
//	private final RoleDetailsRepository roleRepository;
//	private final DepartmentRepository departmentRepository;
//	private final SocialMediaRepository mediaRepository;
//
//	@Bean
//	public ApplicationRunner loadInitialData() {
//		return args -> {
//			// Create a sample company if it doesn't exist
//			Optional<Company> existingCompany = companyRepository.findByCompanyName("Sample Company");
//			Company company;
//			if (existingCompany.isEmpty()) {
//				company = createSampleCompany();
//				companyRepository.save(company);
//				logger.info("Saved sample company: {}", company.getCompanyName());
//			} else {
//				company = existingCompany.get();
//				logger.info("Sample company already exists: {}", company.getCompanyName());
//			}
//			
//
//			// Check if CompanySettings exists for this company, if not create a default one
//			if (company.getCompanySettings() == null) {
//				CompanySettings companySettings = new CompanySettings();
//				companySettings.setCompany(company);
//				companySettings.setUpdateTimeSheet(true); // Set default settings
//				companySettingsRepository.save(companySettings);
//				logger.info("Saved default company settings for: {}", company.getCompanyName());
//			}
//
//			// Define the authorities
//			List<String> roles = List.of("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_EMPLOYEE", "ROLE_HR","ROLE_ACCOUNTANT");
//
//			// Check if authorities already exist, if not, save them
//			for (String role : roles) {
//				if (!authoritiesRepository.existsByName(role)) {
//					Authorities authority = new Authorities();
//					authority.setName(role);
//					authoritiesRepository.save(authority);
//					logger.info("Saved authority: {}", role);
//				}
//			}
//
//			// Define departments and roles
//			List<String> departments = List.of("Admin", "HR", "Developer", "Sales", "Accounts");
//			List<String> employeeRoles = List.of("Software Developer", "HR Executive", "Technical Lead", "senior Accountant");
//
//			// Add departments if they don't exist
//			for (String deptName : departments) {
//				if (!departmentRepository.existsByDepartmentName(deptName)) {
//					Department department = new Department();
//					department.setDepartmentName(deptName);
//					department.setStatus(Status.ENABLED);
//					department.setCompany(company);
//					departmentRepository.save(department);
//					logger.info("Saved department: {}", deptName);
//				}
//			}
//
//			// Add roles if they don't exist
//			for (String roleName : employeeRoles) {
//				if (!roleRepository.existsByRoleName(roleName)) {
//					RoleDetails role = new RoleDetails();
//					role.setRoleName(roleName);
//					role.setStatus(Status.ENABLED);
//					role.setCompany(company);
//					roleRepository.save(role);
//					logger.info("Saved role: {}", roleName);
//				}
//			}
//
//			// Fetch saved authorities from the DB
//			Authorities roleAdmin = authoritiesRepository.findByName("ROLE_ADMIN");
//			Authorities roleManager = authoritiesRepository.findByName("ROLE_MANAGER");
//			Authorities roleEmployee = authoritiesRepository.findByName("ROLE_EMPLOYEE");
//			Authorities roleHr = authoritiesRepository.findByName("ROLE_HR");
//			Authorities roleAccountant = authoritiesRepository.findByName("ROLE_ACCOUNTANT");
//
//			// Fetch departments and roles
//			Department adminDept = departmentRepository.findByDepartmentName("Admin").orElse(new Department());
//			Department hrDept = departmentRepository.findByDepartmentName("HR").orElse(new Department());
//			Department developerDept = departmentRepository.findByDepartmentName("Developer").orElse(new Department());
//			Department salesDept = departmentRepository.findByDepartmentName("Sales").orElse(new Department());
//			Department srAcc = departmentRepository.findByDepartmentName("Accounts").orElse(new Department());
//
//			RoleDetails softwareDevRole = roleRepository.findByRoleName("Software Developer").orElse(new RoleDetails());
//			RoleDetails hrExecRole = roleRepository.findByRoleName("HR Executive").orElse(new RoleDetails());
//			RoleDetails techLeadRole = roleRepository.findByRoleName("Technical Lead").orElse(new RoleDetails());
//			RoleDetails srAccountant = roleRepository.findByRoleName("senior Accountant").orElse(new RoleDetails());
//
//			// Create the HR employee with no manager
//			Employee hr = createEmployee("HR", "Manager", "hr@example.com", roleHr, null, null, company, Status.ENABLED,
//					hrDept, hrExecRole);
//			
//			
//			saveEmployeeIfNotExists(hr);
//			if (hr.getEmpId() != 0) {
//			    saveSocialMedia(hr);
//			}			
//
//			// Create the Admin employee with no manager and assign HR
//			Employee admin = createEmployee("Admin", "User", "admin@example.com", roleAdmin, null, hr, company,
//					Status.ENABLED, adminDept, techLeadRole);
//			saveEmployeeIfNotExists(admin);
//			if (admin.getEmpId() != 0) {
//			    saveSocialMedia(admin);
//			}
//
//			// Create two Manager employees with no manager and assign HR
//			Employee manager1 = createEmployee("Manager1", "Smith", "manager1@example.com", roleManager, admin, hr,
//					company, Status.ENABLED, adminDept, techLeadRole);
//
//			Employee manager2 = createEmployee("Manager2", "Johnson", "manager2@example.com", roleManager, admin, hr,
//					company, Status.ENABLED, salesDept, techLeadRole);
//			saveEmployeeIfNotExists(manager1);
//			
//			if (manager1.getEmpId() != 0) {
//			    saveSocialMedia(manager1);
//			}
//
//			saveEmployeeIfNotExists(manager2);
//			
//			if (manager2.getEmpId() != 0) {
//			    saveSocialMedia(manager2);
//			}
//
//
//			// Create four Employee employees, assign them to the managers, and assign HR
//			Employee emp1 = createEmployee("Employee1", "Brown", "employee1@example.com", roleEmployee, manager1, hr,
//					company, Status.ENABLED, developerDept, softwareDevRole);
//			Employee emp2 = createEmployee("Employee2", "Clark", "employee2@example.com", roleEmployee, manager1, hr,
//					company, Status.ENABLED, developerDept, softwareDevRole);
//			Employee emp3 = createEmployee("Employee3", "Evans", "employee3@example.com", roleEmployee, manager2, hr,
//					company, Status.ENABLED, salesDept, softwareDevRole);
//			Employee emp4 = createEmployee("Employee4", "Davis", "employee4@example.com", roleEmployee, manager2, hr,
//					company, Status.ENABLED, salesDept, softwareDevRole);
//			Employee accountant = createEmployee("ACCOUNTANT", "Alex", "employee5@example.com", roleEmployee, manager2, hr,
//					company, Status.ENABLED, srAcc, srAccountant);
//			saveEmployeeIfNotExists(emp1);
//			if (emp1.getEmpId() != 0) {
//			    saveSocialMedia(emp1);
//			}
//
//			saveEmployeeIfNotExists(emp2);
//			if (emp2.getEmpId() != 0) {
//			    saveSocialMedia(emp2);
//			}
//
//			saveEmployeeIfNotExists(emp3);
//			if (emp3.getEmpId() != 0) {
//			    saveSocialMedia(emp3);
//			}
//			saveEmployeeIfNotExists(emp4);
//			if (emp4.getEmpId() != 0) {
//			    saveSocialMedia(emp4);
//			}
//			saveEmployeeIfNotExists(accountant);
//			if (accountant.getEmpId() != 0) {
//				saveSocialMedia(accountant);
//			}
//		};
//	}
//
//	private Employee createEmployee(String firstName, String lastName, String email, Authorities authority,
//			Employee manager, Employee hr, Company company, Status status, Department department, RoleDetails role) {
//		Employee employee = new Employee();
//		employee.setFirstName(firstName);
//		employee.setLastName(lastName);
//		employee.setEmail(email);
//		employee.setCompanyEmail(firstName.toLowerCase() + ".company@example.com");
//		employee.setPassword(passwordEncoder.encode("password123")); // Password is encoded
//		employee.setMobileNo(1234567890L);
//		employee.setEmergencyContactName("Emergency Contact");
//		employee.setEmergencyContactNo(9876543210L);
//		employee.setDob(LocalDate.of(1990, 1, 1));
//		employee.setDoj(LocalDate.now());
//		employee.setBloodGroup(BloodGroup.B_POSITIVE);
//		employee.setGender(Gender.MALE);
//		employee.setMartialStatus(MaritalStatus.SINGLE);
//		employee.setInsuranceNo("123456789");
//		employee.setEmployementType(EmployementType.Permenant);
//		employee.setStatus(status);
//		employee.setManager(manager);
//		employee.setHr(hr);
//		employee.setCompany(company);
//
//		// Assign department and role to employee
//		employee.setRoles(role);
//		department.setEmployees(List.of(employee));
//		employee.setDepartments(List.of(department));
//
//		// Assign authority to employee
//		Set<Authorities> authoritiesSet = new HashSet<>();
//		authoritiesSet.add(authority);
//		employee.setAuthorities(authoritiesSet);
//		
//	
//		return employee;
//	}
//
//	private void saveSocialMedia(Employee employee) {
//		
//	Optional<SocialMedia> media=	mediaRepository.findByEmployee_EmpId(employee.getEmpId());
//		
//	
//	if(media.isEmpty()) {
//		 // Now associate and save SocialMedia
//	    SocialMedia newMedia = new SocialMedia();
//	    newMedia.setEmployee(employee);  // Set the persisted employee
//
//	
//	 mediaRepository.save(newMedia);
//	}
//		
//	}
//
//	private Employee saveEmployeeIfNotExists(Employee employee) {
//		if (!employeeRepository.existsByEmail(employee.getEmail())) {
//		Employee savedEmployee	=employeeRepository.save(employee);
//			logger.info("Saved employee: {}", employee.getEmail());
//			
//			return savedEmployee;
//		}
//		return employeeRepository.findByEmail(employee.getEmail()).orElse(employee);
//	}
//
//	private Company createSampleCompany() {
//		Company company = new Company();
//		company.setCompanyName("Sample Company");
//		company.setCeo("John Doe");
//		company.setEmail("ceo@example.com");
//		company.setEmpIDPrefix("EMP-");
//		return company;
//	}
//}
package com.ourdept.crm_software.crm.SampleData.Config;


