package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerAttendanceResponseDTO {
    private long attendanceId;
    private LocalDateTime punchInTime;
    private LocalDateTime punchOutTime;
    private String punchInMessage;
    private String punchOutMessage;
    private String managerRequestMessage;
    private String punchoutStatus;
    private String attendanceStatus;
    private String employeeName;  // Additional field for employee's name
}
