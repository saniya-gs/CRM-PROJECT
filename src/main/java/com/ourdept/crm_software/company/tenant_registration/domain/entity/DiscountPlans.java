package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.List;

import com.ourdept.crm_software.company.tenant_registration.domain.enums.MonthPlan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DiscountPlans {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discountPlanId")
	long discountPlanId;

	@Column(name = "monthPlanType")
	MonthPlan monthPlanType;

	@Column(name = "percentage")
	double percentage;// discount per user

	@Column(name = "cuponCode")
	String cuponCode;

	@Column(name = "purchaseLimit")
	int purchaseLimit;

	@Column(name = "noOfPurchase")
	int noOfPurchase;// used the cupon code

	@ManyToMany
	@JoinTable(name = "discountPlans_modules", joinColumns = @JoinColumn(name = "discountPlanId"), inverseJoinColumns = @JoinColumn(name = "moduleId"))
	List<Modules> modules;
}
