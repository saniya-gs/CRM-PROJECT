package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLeaveHistoryListDTO {
    private long leaveId;
    private String employeeName;
    private String role;
    private String department;
    private int totalLeaves;
    private int usedLeaves;
    private int availableLeaves;
    private String leaveTypes;
}