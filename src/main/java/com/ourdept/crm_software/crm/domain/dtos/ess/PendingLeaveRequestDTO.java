package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.util.List;

import com.ourdept.crm_software.crm.domain.enums.LeaveRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingLeaveRequestDTO {
    private long leaveId;
    private String employeeName;
    private String role;
    private String department;
    LeaveRequest leaveStatus;//
    List<LocalDate> dates;
    
 
}