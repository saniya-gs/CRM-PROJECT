package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificationRequestDTO {
    private long certificationId;
    private String certificationName;
    private String description;
    private LocalDate certifiedOn;
    private String institutionName;
}
