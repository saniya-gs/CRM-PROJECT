package com.ourdept.crm_software.crm.domain.dtos;

import com.ourdept.crm_software.crm.domain.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDTO {
    public DepartmentResponseDTO(long departmentId, String departmentName, Object status2, Status status3) {
		// TODO Auto-generated constructor stub
	}
	private long departmentId;
    private String departmentName;
    private String departmentManagerName;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String companyName;
}
