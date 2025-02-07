package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ourdept.crm_software.crm.domain.enums.PunchoutStatus;
import com.ourdept.crm_software.crm.utils.CustomLocalTimeDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PunchOutResponseDTO {
    private long attendanceId;
    private LocalDate attendanceDate;
    @Schema(example = "08:00 am", description = "if PunchIn Time is null , user have not punched in")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime punchInTime;
    @Schema(example = "08:00 am", description = "if PunchIn Time is null , user have not punched in")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime punchOutTime;
    
    @Enumerated(EnumType.STRING)
    private PunchoutStatus punchOutStatus;
    
    private String managerRequestMessage;
    private String punchOutMessage;
    private List<WorkTaskResponseDTO> workTasks =new ArrayList<>();

}
