package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.util.List;

import com.ourdept.crm_software.crm.domain.dtos.WorkingDaysResponseDTO;
import com.ourdept.crm_software.crm.domain.enums.BloodGroup;
import com.ourdept.crm_software.crm.domain.enums.EmployementType;
import com.ourdept.crm_software.crm.domain.enums.Gender;
import com.ourdept.crm_software.crm.domain.enums.MaritalStatus;
import com.ourdept.crm_software.crm.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfileResponseDTO {
    private long empId;
    private String firstName;
    private String lastName;
    private String email;
    private String companyEmail;
    private long mobileNo;
    private String emergencyContactName;
    private long emergencyContactNo;
    private LocalDate dob;
    private LocalDate doj;
    private BloodGroup bloodGroup;
    private Gender gender;
    private MaritalStatus martialStatus;
    private EmployementType employementType;
    private Status status;
    private String managerName;
    private String hrName;
    private String role;
    private String branch;
    private List<String> departments;
    private String companyName;
    private WorkingDaysResponseDTO workingDays;
    private OfficeTimingResponseDTO officeTimings;
    private List<String> authorities;
}