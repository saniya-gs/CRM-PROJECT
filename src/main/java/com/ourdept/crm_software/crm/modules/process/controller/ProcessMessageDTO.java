package com.ourdept.crm_software.crm.modules.process.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessMessageDTO {
    private Long id; // Message ID
    private String message; // Message text
    private Long processHistoryId; // ID of the associated ProcessHistory
}
