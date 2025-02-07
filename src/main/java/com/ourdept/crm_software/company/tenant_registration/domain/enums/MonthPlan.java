package com.ourdept.crm_software.company.tenant_registration.domain.enums;

public enum MonthPlan {
	THREE_MONTHS(3, "3 Months"), SIX_MONTHS(6, "6 Months"), TWELVE_MONTHS(12, "12 Months");

	private final int months;
	private final String description;

	MonthPlan(int months, String description) {
		this.months = months;
		this.description = description;
	}

	public int getMonths() {
		return months;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}
}
