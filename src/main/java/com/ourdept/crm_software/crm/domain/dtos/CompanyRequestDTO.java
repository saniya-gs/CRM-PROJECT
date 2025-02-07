package com.ourdept.crm_software.crm.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {
    private long companyId; // Used for update
    private String companyName;
    private String empIDPrefix;
    private String ceo;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String state;
    private String city;
    private int pincode;
}
