package com.ourdept.crm_software.crm.domain.dtos.ess;

import com.ourdept.crm_software.crm.domain.enums.BloodGroup;
import com.ourdept.crm_software.crm.domain.enums.Gender;
import com.ourdept.crm_software.crm.domain.enums.MaritalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDTO {
    private long mobileNo;
    private String personalEmail;
    private String emergencyContactName;
    private long emergencyContactNo;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private BloodGroup bloodGroup;
    private byte[] profileImage;
    private String profileImageName;
    private String profileImageType;
}
