package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeDTO {
	long leaveTypeId;
    private String leaveTypeName;
    private int totalLeaves;
    private int usedLeaves;
    private int availableLeaves;
}
