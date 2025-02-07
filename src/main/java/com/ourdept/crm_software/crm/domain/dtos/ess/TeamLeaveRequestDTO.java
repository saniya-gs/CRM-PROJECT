package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.util.List;

import com.ourdept.crm_software.crm.domain.enums.LeaveDuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamLeaveRequestDTO {
	long employeeId;
    private long leaveId;
    private LeaveDuration leaveDuration;
    private int noOfDays;
    private String comments;
    private String responsibility;
    private List<LocalDate> leaveDates;
    private List<Long> fallBackEmployeeIds;
    private long leaveTypeId;
}
