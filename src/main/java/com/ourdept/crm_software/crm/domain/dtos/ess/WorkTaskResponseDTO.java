package com.ourdept.crm_software.crm.domain.dtos.ess;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkTaskResponseDTO {
	private long workTaskId;
    private String projectName;
    private String task;
    private int workedHours;
    private int workedMinutes;
}
