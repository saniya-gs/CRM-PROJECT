package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;

import com.ourdept.crm_software.crm.domain.enums.QualificationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationResponseDTO {
    private long qualificationId;
    private QualificationType qualificationType;
    private String degreeName;
    private String institution;
    private double percentage;
    private LocalDate courseStartDate;
    private LocalDate courseEndDate;
    private String employeeName;  // To show employee's name in response
}
