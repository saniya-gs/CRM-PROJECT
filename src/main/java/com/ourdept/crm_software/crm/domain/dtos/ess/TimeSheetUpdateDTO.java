package com.ourdept.crm_software.crm.domain.dtos.ess;

import com.ourdept.crm_software.crm.domain.enums.TimeSheetStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheetUpdateDTO {
    private long timeSheetId;  // ID of the TimeSheet to be updated
    private TimeSheetStatus managerApproval;  // Manager Approval status (APPROVED, REJECTED)
}