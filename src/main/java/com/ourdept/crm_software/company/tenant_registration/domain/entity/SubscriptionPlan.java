package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.List;

import com.ourdept.crm_software.company.tenant_registration.domain.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class SubscriptionPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscriptionPlanId")
	long subscriptionPlanId;

	@Column(name = "planName")
	String planName;

	@Column(name = "planSequence")
	int planSequence;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	Status status;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SubscriptionPlan_modulefeature", joinColumns = @JoinColumn(name = "subscriptionPlanId"), inverseJoinColumns = @JoinColumn(name = "moduleFeatureId"))
	List<ModuleFeature> modulefeature ;

	@OneToMany(mappedBy = "subscriptionPlan")
	List<SubscriptionPlanFeature> mainPlanFeatures;

	@OneToMany(mappedBy = "subscriptionPlan")
	List<ModulePricingPlan> modulepricehistories ;

	@OneToMany(mappedBy = "subscriptionPlan")
	List<PurchaseRequest> purchaseRequests ;

	@OneToMany(mappedBy = "subscriptionPlan")
	List<PurchaseDetails> purchaseDetails ;

}
