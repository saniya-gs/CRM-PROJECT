package com.ourdept.crm_software.crm.modules.process.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    private Long id;
    private Double billAmount;
    private String orderStatus;
    private String paymentStatus;
    private LocalDate paidDate;
    private List<String> processHistories;
}
