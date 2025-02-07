package com.ourdept.crm_software.crm.domain.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingDaysRequestDTO {

    private List<Long> branchIds;
    private List<Long> departmentIds;

    
    private long workingDaysId;
    private String workingTypeName;

    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;

    private boolean leaveOnFirstSaturday;
    private boolean leaveOnFirstSunday;
    private boolean leaveOnSecondSaturday;
    private boolean leaveOnSecondSunday;
    private boolean leaveOnThirdSaturday;
    private boolean leaveOnThirdSunday;
    private boolean leaveOnFourthSaturday;
    private boolean leaveOnFourthSunday;
    private boolean leaveOnFifthSaturday;
    private boolean leaveOnFifthSunday;

    private long companyId; // Reference to the company for validation purposes
}
