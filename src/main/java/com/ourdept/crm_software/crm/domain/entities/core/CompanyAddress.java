package com.ourdept.crm_software.crm.domain.entities.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class CompanyAddress extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "addressId")
	long addressId;
	
	@Column(name = "addressLine1")
	String addressLine1;
	
	@Column(name = "addressLine2")
	String addressLine2;
	
	@Column(name = "country")
	String country;
	
	@Column(name = "state")
	String state;
	
	@Column(name = "city")
	String city;
	
	@Column(name = "pincode")
	int pincode;

	@OneToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")  // Foreign key reference to Company
	Company company;
}
