package com.ourdept.crm_software.crm.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAddressResponseDTO {
    private long addressId;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String state;
    private String city;
    private int pincode;
}
