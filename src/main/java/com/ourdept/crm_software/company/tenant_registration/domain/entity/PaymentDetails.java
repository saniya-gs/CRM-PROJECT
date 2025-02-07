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
public class PaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "paymentId")
	long paymentId; 
	
	@Column(name = "paymentType")
	String paymentType; 
	
	@Column(name = "transactionId")
	int transactionId; 
	
	@Column(name = "amountPaid")
	double amountPaid; 
	
	@Column(name = "description")	
	String description;
	
//	String transactionDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchaseDetailsId", referencedColumnName = "purchaseDetailsId") // Foreign key reference to
	PurchaseDetails purchaseDetails;
	

}
