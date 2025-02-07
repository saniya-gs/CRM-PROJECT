package com.ourdept.crm_software.crm.domain.dtos;

import com.ourdept.crm_software.crm.domain.enums.PayrollType;
import com.ourdept.crm_software.crm.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypesResponseDTO {
    private long leaveTypeId;
    private String leaveType; // sick, casual, privilege leave
    private int periodNoDays; // per year
    private PayrollType payrollType; // (paid|unpaid)
    private boolean carryForward;
    private boolean enchasment;
    private int noOfYears;
    private Status status;
    private String companyName;
}
