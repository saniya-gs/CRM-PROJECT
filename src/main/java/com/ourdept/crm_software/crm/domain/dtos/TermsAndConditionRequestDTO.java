package com.ourdept.crm_software.crm.domain.dtos;

import com.ourdept.crm_software.crm.domain.enums.TermsAndConditionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TermsAndConditionRequestDTO {
    private long termsAndConditionId;
    private TermsAndConditionType type;  // Terms/condition

    private String info;
    private long companyId;
}
