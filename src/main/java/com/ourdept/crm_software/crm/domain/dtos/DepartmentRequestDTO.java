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
public class DepartmentRequestDTO {
    private long deptId;
    private String deptName;
    @Enumerated(EnumType.STRING)
    private Status status;
    private long companyId;
}
