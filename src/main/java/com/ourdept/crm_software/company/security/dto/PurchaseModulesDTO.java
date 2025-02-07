package com.ourdept.crm_software.company.security.dto;

import java.time.LocalDate;
import java.util.List;

public record PurchaseModulesDTO(
	    long purchaseDetailsId,
	    String transactionId,
	    LocalDate purchasedAt,
	    LocalDate validTill,
	    List<ModuleAccessDTO> modules
	) {
	}
