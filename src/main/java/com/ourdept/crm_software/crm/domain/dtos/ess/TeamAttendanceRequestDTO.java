package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamAttendanceRequestDTO {
    private List<Long> employeeIds;   // List of employee IDs
    
    private LocalDate attendanceDate;     // Punch in or punch out time
    private LocalTime punchTime;     // Punch in or punch out time
    private String punchType;            // Punch In or Punch Out
    private long attendanceTypeId;        // Attendance type (foreign key to AttendanceType)
}
