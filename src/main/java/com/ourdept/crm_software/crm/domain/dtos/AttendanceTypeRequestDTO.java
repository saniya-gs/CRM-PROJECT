package com.ourdept.crm_software.crm.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceTypeRequestDTO {
    private long attendanceTypeId;
    private String attendanceType;
    private long companyId;
}
