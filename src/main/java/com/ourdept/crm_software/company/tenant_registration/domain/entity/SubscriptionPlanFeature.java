package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class SubscriptionPlanFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscriptionPlanFeatureId")
	long subscriptionPlanFeatureId;
	
	@Column(name = "mainPlanFeatureName")
	String mainPlanFeatureName;

	@Column(name = "FeatureDescription")
	String FeatureDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriptionPlanId", referencedColumnName = "subscriptionPlanId") // Foreign key reference to
	SubscriptionPlan subscriptionPlan;

}