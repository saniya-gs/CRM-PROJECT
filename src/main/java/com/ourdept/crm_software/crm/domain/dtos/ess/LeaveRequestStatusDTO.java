package com.ourdept.crm_software.crm.domain.dtos.ess;

import com.ourdept.crm_software.crm.domain.enums.LeaveRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestStatusDTO {
    private long leaveId;
    private LeaveRequest leaveStatus; // REQUESTED, APPROVED, REJECTED, REVOKE
}
