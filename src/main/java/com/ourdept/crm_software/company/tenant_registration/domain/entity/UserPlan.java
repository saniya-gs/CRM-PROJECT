package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.List;

import com.ourdept.crm_software.company.tenant_registration.domain.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class UserPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userPlanId")
	long userPlanId;

	@Column(name = "minimumUser")
	int minimumUser;

	@Column(name = "maximumUser")
	int maximumUser;

	@OneToMany(mappedBy = "userPlan")
	List<ModulePricingPlan> modulepricehistories ;

	@OneToMany(mappedBy = "userPlan")
	List<PurchaseDetails> purchaseDetails ;

	@OneToMany(mappedBy = "userPlan")
	List<PurchaseRequest> purchaseRequests ;

	@Enumerated(EnumType.STRING) 
	@Column(name = "status")
	Status status;

}
