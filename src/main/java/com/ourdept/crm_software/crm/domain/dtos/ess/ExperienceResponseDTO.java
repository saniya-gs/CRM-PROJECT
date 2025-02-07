package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceResponseDTO {
    private long experienceId;
    private String companyName;
    private String designation;
    private LocalDate workedFrom;
    private LocalDate workedTill;
    private String description;
    private String employeeName;  // Display employee's name in the response
}
