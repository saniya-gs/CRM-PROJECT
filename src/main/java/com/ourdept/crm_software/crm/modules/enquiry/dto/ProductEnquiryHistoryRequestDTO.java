package com.ourdept.crm_software.crm.modules.enquiry.dto;

import com.ourdept.crm_software.crm.domain.entities.crm.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEnquiryHistoryRequestDTO {
    private Product product;
    private Integer quantity;
}
