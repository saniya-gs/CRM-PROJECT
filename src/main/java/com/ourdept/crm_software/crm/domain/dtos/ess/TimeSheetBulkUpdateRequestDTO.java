package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheetBulkUpdateRequestDTO {
    private List<TimeSheetUpdateDTO> timeSheets;
}