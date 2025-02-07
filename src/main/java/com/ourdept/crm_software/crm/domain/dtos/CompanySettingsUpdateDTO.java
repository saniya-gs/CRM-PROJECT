package com.ourdept.crm_software.crm.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanySettingsUpdateDTO {
    private long companyId;
    private boolean updateTimeSheet;  // This will be set to true to update the timesheet setting
}
