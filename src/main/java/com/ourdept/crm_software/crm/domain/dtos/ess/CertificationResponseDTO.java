package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificationResponseDTO {
    private long certificationId;
    private String certificationName;
    private String description;
    private LocalDate certifiedOn;
    private String institutionName;
    private String employeeName;  // Display employee's name in response
}
