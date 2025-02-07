package com.ourdept.crm_software.crm.domain.dtos;

import java.util.List;

import com.ourdept.crm_software.crm.domain.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchResponseDTO {
    private long branchId;
    private String branchName;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BranchAddressResponseDTO branchAddress;
    private List<String> departmentNames;
    
}