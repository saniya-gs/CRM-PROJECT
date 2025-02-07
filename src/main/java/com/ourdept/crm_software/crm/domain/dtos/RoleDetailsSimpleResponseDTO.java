package com.ourdept.crm_software.crm.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDetailsSimpleResponseDTO {
    private long roleId;
    private String roleName;
}
