package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.time.LocalDate;

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
public class ModulePricingPlan
{
    @Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "modulePricingPlanId")
    long  modulePricingPlanId; 
    
	@Column(name = "planAmountPerUser")    
    double planAmountPerUser; 
	
	@Column(name = "priceAllotedAt")
    LocalDate priceAllotedAt; 
	
	@Column(name = "priceAllotedTill")   
    LocalDate priceAllotedTill;
    
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moduleId", referencedColumnName = "moduleId") // Foreign key reference to
	Modules modules;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriptionPlanId", referencedColumnName = "subscriptionPlanId") // Foreign key reference to
	SubscriptionPlan subscriptionPlan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userPlanId", referencedColumnName = "userPlanId") // Foreign key reference to
	UserPlan userPlan;
    
}