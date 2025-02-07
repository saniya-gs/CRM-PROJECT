package com.ourdept.crm_software.crm.domain.dtos;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ourdept.crm_software.crm.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeTimingResponseDTO {
    private long officeTimingId;
    private String shiftName;
    private int offTime;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime shiftForm;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime shiftTo;
    
    private Status status;
    
    private String companyName;
    
    private List<BranchListResponseDTO> branches;
    ;  // List of branch names associated with the office timing
}

