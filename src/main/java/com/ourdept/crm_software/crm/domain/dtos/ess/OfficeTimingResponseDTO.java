package com.ourdept.crm_software.crm.domain.dtos.ess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeTimingResponseDTO {
    private LocalTime shiftFrom;
    private LocalTime shiftTo;
}
