package com.ourdept.crm_software.crm.domain.dtos;

import java.time.LocalDate;

import com.ourdept.crm_software.crm.domain.enums.HolidayType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class HolidayResponseDTO {
    private long holidayId;
    private String holidayName;
    private LocalDate eventDate;
    @Enumerated(EnumType.STRING)
    private HolidayType holidayType;
    private int noOfdays;

    
}
