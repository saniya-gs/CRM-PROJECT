package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveHistoryStatusRequestDTO {
    public LeaveHistoryStatusRequestDTO(int leaveId2, String string, LocalDate datefrom, LocalDate dateTo, String name,
			String leaveType, String string2, String comments) {
		// TODO Auto-generated constructor stub
	}

	private long leaveId;
}
