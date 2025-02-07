package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerFallbackResponseDTO {
    private String fallbackTo;     // Employee on leave (subordinate)
    private String fallbackFrom;   // Employee who assigned the fallback
    private String responsibility; // Responsibility assigned
    private List<LocalDate> leaveDates;
}
