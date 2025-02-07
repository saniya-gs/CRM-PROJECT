package com.ourdept.crm_software.crm.domain.dtos.ess;

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
public class TimeSheetRequestDTO {
    private long timeSheetId;
    @Enumerated(EnumType.STRING)
    private TimeSheetStatus managerApproval;
    private List<WorkTaskRequestDTO> workTaskSheets;
}
