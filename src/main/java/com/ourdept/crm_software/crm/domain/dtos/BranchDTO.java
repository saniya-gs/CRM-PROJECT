package com.ourdept.crm_software.crm.domain.dtos;

import lombok.Data;

@Data
public class BranchDTO {
    private long branchId;
    private String branchName;

    public BranchDTO(long branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }
}
