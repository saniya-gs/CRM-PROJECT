package com.ourdept.crm_software.company.security.dto;

import java.util.List;

public record ModuleAccessDTO(
	    String moduleAccessID,         // Unique ID for the module
	    List<String> moduleFeatureAccessIDs // List of feature access IDs for the module
	) {
	}
