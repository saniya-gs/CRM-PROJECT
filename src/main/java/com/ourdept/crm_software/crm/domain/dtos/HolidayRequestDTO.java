package com.ourdept.crm_software.crm.domain.dtos;


import java.time.LocalDate;
import java.util.List;

import com.ourdept.crm_software.crm.domain.enums.HolidayType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class HolidayRequestDTO {
    private long holidayId;
    private String holidayName;
    private LocalDate eventDate;
    @Enumerated(EnumType.STRING)
    private HolidayType holidayType;
    private int noOfdays;
    private List<Long> branchIds; // IDs of branches mapped to the holiday
}
