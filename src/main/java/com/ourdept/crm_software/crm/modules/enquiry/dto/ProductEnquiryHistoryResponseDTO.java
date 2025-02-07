package com.ourdept.crm_software.crm.modules.enquiry.dto;

import com.ourdept.crm_software.crm.domain.enums.crm.EnquiryStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEnquiryHistoryResponseDTO {
    private Long id;
    private String productName;
    private Integer quantity;
    private EnquiryStatus status;
}
