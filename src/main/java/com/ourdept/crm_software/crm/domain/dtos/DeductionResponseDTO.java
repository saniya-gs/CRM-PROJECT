package com.ourdept.crm_software.crm.domain.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductionResponseDTO {
    private long otherDeductionId;
    private String deductionName;
    private List<String> branchNames;
}