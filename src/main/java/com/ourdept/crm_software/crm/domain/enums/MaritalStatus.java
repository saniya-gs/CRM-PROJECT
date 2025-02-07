package com.ourdept.crm_software.crm.domain.enums;

public enum MaritalStatus {
	SINGLE("Single"), MARRIED("Married"), DIVORCED("Divorced"), WIDOWED("Widowed"), SEPARATED("Separated"),
	OTHER("Other");

	private final String value;

	MaritalStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
