package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerApprovalResponseDTO {
    private long attendanceId;
    private LocalTime punchOutTime;
    private String punchOutStatus;
    private String attendanceStatus;
}
