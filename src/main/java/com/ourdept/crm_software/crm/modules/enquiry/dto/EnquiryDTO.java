package com.ourdept.crm_software.crm.modules.enquiry.dto;

import java.util.List;

import com.ourdept.crm_software.crm.domain.entities.crm.Client;
import com.ourdept.crm_software.crm.domain.enums.crm.EnquiryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryDTO {
    private Client client;
    private String contactPerson;
    private String email;
    private String mobileNumber;
    private String city;
    private String state;
    private Double discountPercentage;
    private String notes;
    private EnquiryType type;
    private List<ProductEnquiryHistoryRequestDTO> productHistories;
}
