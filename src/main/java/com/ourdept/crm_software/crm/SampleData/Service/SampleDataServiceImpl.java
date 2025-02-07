//package com.ourdept.crm_software.crm.SampleData.Service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.hr_labs.our_dept_com.hrm.repository.AttendanceRepository;
//import com.hr_labs.our_dept_com.hrm.repository.EmployeeLeaveTypeHistoryRepository;
//import com.hr_labs.our_dept_com.hrm.repository.LeaveHistoryRepository;
//import com.hr_labs.our_dept_com.hrm.repository.TimeSheetRepository;
//import com.ourdept.crm_software.crm.domain.entities.Core.Employee;
//import com.ourdept.crm_software.crm.repository.AddressRepository;
//import com.ourdept.crm_software.crm.repository.BranchRepository;
//import com.ourdept.crm_software.crm.repository.CertificationRepository;
//import com.ourdept.crm_software.crm.repository.CompanyRepository;
//import com.ourdept.crm_software.crm.repository.EmployeeRepository;
//import com.ourdept.crm_software.crm.repository.ExperienceRepository;
//import com.ourdept.crm_software.crm.repository.OfficeTimingRepository;
//import com.ourdept.crm_software.crm.repository.QualificationRepository;
//import com.ourdept.crm_software.crm.repository.SocialMediaRepository;
//@Service
//public class SampleDataServiceImpl implements SampleDataService {
//
//    @Autowired
//    private CertificationRepository certificationRepository;
//    @Autowired
//    private ExperienceRepository experienceRepository;
//    @Autowired
//    private QualificationRepository qualificationRepository;
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//    @Autowired
//    private LeaveHistoryRepository leaveHistoryRepository;
//    @Autowired
//    private EmployeeLeaveTypeHistoryRepository employeeLeaveTypeHistoryRepository;
//   
//    @Autowired
//    private TimeSheetRepository timeSheetsRepository;
//    @Autowired
//    private SocialMediaRepository socialMediaRepository;
//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    @Autowired
//    private BranchRepository branchRepository;
//    @Autowired
//    private OfficeTimingRepository officeTimingRepository;
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    @Override
//    @Transactional
//    public void deleteAllSampleData() {
//        // Step 1: Delete dependent child entities (One-to-Many relationships)
//        certificationRepository.deleteAll();
//        experienceRepository.deleteAll();
//        qualificationRepository.deleteAll();
//        attendanceRepository.deleteAll();
//        leaveHistoryRepository.deleteAll();
//        employeeLeaveTypeHistoryRepository.deleteAll();
//        timeSheetsRepository.deleteAll();
//        socialMediaRepository.deleteAll();
//        addressRepository.deleteAll();
//
//        // Step 2: Handle many-to-many relationships by clearing the collections in the Employee entity
//        List<Employee> employees = employeeRepository.findAll();  // Get all employees
//
//        for (Employee employee : employees) {
//            employee.getDepartments().clear();    // Clear department association
//            employee.getHolidays().clear();       // Clear holidays association
//
//            employeeRepository.save(employee);    // Persist the changes, JPA will automatically update the join table
//        }
//
//        // Step 3: Delete employees
//        employeeRepository.deleteAll();  // Now employees can be safely deleted
//
//        // Step 4: Delete branches (after employees are removed)
//        branchRepository.deleteAll();
//
//        // Step 5: Delete office timings
//        officeTimingRepository.deleteAll();
//
//        // Step 6: Delete companies
//        companyRepository.deleteAll();
//    }
//}
