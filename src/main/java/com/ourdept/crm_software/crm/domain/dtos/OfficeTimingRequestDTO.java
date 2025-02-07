package com.ourdept.crm_software.crm.domain.dtos;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ourdept.crm_software.crm.domain.enums.Status;
import com.ourdept.crm_software.crm.utils.CustomLocalTimeDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeTimingRequestDTO {
	
    private long officeTimingId;
    
    private String shiftName;
    
    private int offTime;
    
    @Schema(example = "08:00 am")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime shiftForm;
    
    @Schema(example = "08:00 am")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime shiftTo;
    
    private Status status;
    
    private long companyId;
    
    private List<Long> branchIds;  // List of branch IDs to map
}
