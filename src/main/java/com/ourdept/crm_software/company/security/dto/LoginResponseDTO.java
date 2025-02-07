package com.ourdept.crm_software.company.security.dto;

import java.util.List;

public record LoginResponseDTO(String status,
		String employeeName, 
		List<String> authorities, 
		String role,
		String profileImageName,
		String profileImageType,
		byte[] profileImage, 
		String logoName, 
		String logoType,
		byte[] logo,
		 List<ModuleAccessDTO> moduleAccess // New Field
		) {

}