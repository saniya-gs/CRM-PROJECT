package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamAttendanceResponseDTO {
    private long employeeId;
    private String employeeName;
    private String message;   // Success or error message (e.g., "Punch In saved", "Punch Out already exists")
}
