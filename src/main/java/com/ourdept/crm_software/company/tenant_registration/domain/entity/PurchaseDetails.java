package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
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
public class PurchaseDetails {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY) 
	@Column(name = "purchaseDetailsId")
	long purchaseDetailsId; 
	
	@Column(name = "purchaseId")
	String purchaseId; 
	
	@Column(name = "paymentMode")
	String paymentMode; 
	
	@Column(name = "totalAmount")
	double totalAmount; 
	
	@Column(name = "purchaseDate")
	LocalDate purchaseDate; 
	
	@Column(name = "balanceAmount")
	double balanceAmount; 
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenantRegId", referencedColumnName = "tenantRegId") // Foreign key reference to
	Tenants tenants;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "purchaseDetails_modules", joinColumns = @JoinColumn(name = "purchaseDetailsId"), inverseJoinColumns = @JoinColumn(name = "moduleId"))
	List<Modules> modules;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	List<PaymentDetails> paymentDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userPlanId", referencedColumnName = "userPlanId") // Foreign key reference to
	UserPlan userPlan;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriptionPlanId", referencedColumnName = "subscriptionPlanId") // Foreign key reference to
	SubscriptionPlan subscriptionPlan;
	
	

	

}
