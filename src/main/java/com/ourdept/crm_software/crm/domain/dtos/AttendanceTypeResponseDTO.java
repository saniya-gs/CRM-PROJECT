package com.ourdept.crm_software.crm.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceTypeResponseDTO {
   
	private long attendanceTypeId;
    private String attendanceType;
    private boolean updateTimeSheet;
    private String companyName;
}
