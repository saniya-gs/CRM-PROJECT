package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class PurchaseRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchaseRequestId")
	long purchaseRequestId;

	@Column(name = "totalAmount")
	double totalAmount;

	@Column(name = "purchaseRequestDate")
	LocalDate purchaseRequestDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenantRegId", referencedColumnName = "tenantRegId") // Foreign key reference to
	Tenants tenants;

	@ManyToMany
	@JoinTable(name = "purchaseRequest_modules", joinColumns = @JoinColumn(name = "purchaseRequestId"), inverseJoinColumns = @JoinColumn(name = "moduleId"))
	List<Modules> modules;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userPlanId", referencedColumnName = "userPlanId") // Foreign key reference to
	UserPlan userPlan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriptionPlanId", referencedColumnName = "subscriptionPlanId") // Foreign key reference to
	SubscriptionPlan subscriptionPlan;
}
