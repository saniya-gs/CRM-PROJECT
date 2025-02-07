package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FallbackLeaveResponseDTO {
    private String fallBackFrom;   // Employee who assigned the fallback
    private String responsibility; // Responsibility assigned during leave
    private List<LocalDate> leaveDates;
}
