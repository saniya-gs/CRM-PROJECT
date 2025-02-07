package com.ourdept.crm_software.crm.domain.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchListWithDepartmentsResponseDTO {
    private long branchId;
    private String branchName;
    private List<DepartmentSimpleResponseDTO> departments;
}
