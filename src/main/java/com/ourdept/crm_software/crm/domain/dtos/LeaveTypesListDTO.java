package com.ourdept.crm_software.crm.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypesListDTO {
    private long leaveTypeId;
    private String leaveType; // sick, casual, privilege leave
  
}
