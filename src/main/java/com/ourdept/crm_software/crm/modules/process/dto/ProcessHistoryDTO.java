package com.ourdept.crm_software.crm.modules.process.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessHistoryDTO {
    private Long id; // ProcessHistory ID
    private String processStageName; // Name of the process stage
    private String productName; // Name of the product associated with the process
    private String status; // Status of the process (ASSIGNED, COMPLETED, RETURNED)
    private Boolean returned; // Whether the process has been returned
    private String employeeAssignedName; // Name of the assigned employee (if any)
}
