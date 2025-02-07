package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.util.List;

import com.ourdept.crm_software.crm.domain.enums.TimeSheetStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheetResponseDTO {
   

	private long timeSheetId;
    private LocalDate timesheetDate;
    @Enumerated(EnumType.STRING)
    private TimeSheetStatus managerApproval;
//    in minutes
    int workedDuration;

    private List<WorkTaskResponseDTO> workTaskSheets;
}
