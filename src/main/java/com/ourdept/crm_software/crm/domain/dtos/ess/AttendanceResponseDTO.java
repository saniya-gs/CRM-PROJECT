package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ourdept.crm_software.crm.domain.enums.PunchoutStatus;
import com.ourdept.crm_software.crm.utils.CustomLocalTimeDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceResponseDTO {
    private long attendanceId;
    private long attendanceTypeId;
    private String EmployeeName;
    @Schema(example = "08:00 am", description = "if PunchIn Time is null , user have not punched in")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime punchInTime;   
    private LocalDate attendanceDate;
    private String punchInMessage;
    @Schema(example = "08:00 am", description = "if PunchIn Time is null , user have not punched in")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime punchOutTime;
    private String punchOutMessage;
    private PunchoutStatus punchoutStatus;
    private List<WorkTaskResponseDTO> workTasks;
    private String managerRequestMessage;
    
    private boolean attendanceStatus;


}
