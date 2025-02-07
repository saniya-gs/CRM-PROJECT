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
public class RoleDetailsResponseDTO {
    public RoleDetailsResponseDTO(long roleId2, String roleName2, int roleSequence2, String roleStatus2,
			Object companyName2) {
		// TODO Auto-generated constructor stub
	}
	private long roleId;
    private String roleName;
    private int roleSequence;
    @Enumerated(EnumType.STRING)
    private Status roleStatus;
    private String companyName;
}
