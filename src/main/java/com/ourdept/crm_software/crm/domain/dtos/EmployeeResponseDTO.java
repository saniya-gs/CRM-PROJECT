package com.ourdept.crm_software.crm.domain.dtos;

import java.time.LocalDate;
import java.util.List;

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
public class EmployeeResponseDTO {
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
    private long managerId;
    private long hrId;
    private long roleId;
    private long branchId;
    private long companyId;
    private long workingDaysId;
    private long officeTimingId;
    private List<Long> authorityIds;
}