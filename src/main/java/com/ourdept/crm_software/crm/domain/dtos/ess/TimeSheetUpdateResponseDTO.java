package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;

import com.ourdept.crm_software.crm.domain.enums.TimeSheetStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheetUpdateResponseDTO {
    private long timeSheetId;
    private LocalDate timesheetDate;
    private TimeSheetStatus managerApproval;
}

