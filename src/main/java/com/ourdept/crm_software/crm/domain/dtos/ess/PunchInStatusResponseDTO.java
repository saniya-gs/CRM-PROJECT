package com.ourdept.crm_software.crm.domain.dtos.ess;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ourdept.crm_software.crm.domain.dtos.AttendanceTypeResponseDTO;
import com.ourdept.crm_software.crm.utils.CustomLocalTimeDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PunchInStatusResponseDTO {
//	@Schema(name = "Product ID", example = "1", required = true,description = "if PunchIn Time is null , user have not punched in")
	@Schema(example = "08:00 am", description = "if PunchIn Time is null , user have not punched in")
    @JsonDeserialize(using = CustomLocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    private LocalTime punchInTime; // will be null if not punched In
	private LocalDate atendanceDate;
	private long attendanceId;
    private String attendanceType;
    private boolean updateTimeSheet;
    private OfficeTimingResponseDTO officeTiming;
    private String message;
    private boolean attendanceStatus;
    private List<AttendanceTypeResponseDTO> attendanceTypes; // New field to handle attendance types when not punched in
    TimeSheetRequestDTO timeSheetResponseDTO;
}

