package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ourdept.crm_software.crm.utils.CustomLocalTimeDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceReportResponseDTO {
	
    private long employeeId;
    private String employeeName;
    private List<String> departmentName;
    private String roleName;
	private LocalDate atendanceDate;
	@Schema(example = "08:00 am", description = "if PunchIn Time is null , user have not punched in")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime punchInTime;
	@Schema(example = "08:00 am", description = "if PunchIn Time is null , user have not punched in")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime punchOutTime;
    private String punchoutStatus;
    private int attendanceTypeId;
    private String attendanceType;
    private LocalDate attendanceDate;

}
