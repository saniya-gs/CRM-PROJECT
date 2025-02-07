package com.ourdept.crm_software.crm.domain.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkingDaysResponseDTO {

    private long workingDayId;

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

//    private Integer branchId;   // Referencing the associated branch
//
//    private Integer departmentId; // Referencing the associated department
//
//    private Integer companyId;  // Referencing the associated company
    private String companyName; // Human-readable name of the company
    
    private List<BranchListWithDepartmentsResponseDTO> branches;      // Names of branches associated with this working day
}

