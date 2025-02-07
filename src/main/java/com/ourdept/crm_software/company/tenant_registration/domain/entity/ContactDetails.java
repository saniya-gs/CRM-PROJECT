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
public class ContactDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "contactDetailsId")
	long contactDetailsId;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "email")
	String email; 
	
	@Column(name = "mobileNo")
	long mobileNo; 
	
	@Column(name = "role")
	String role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenantRegId", referencedColumnName = "tenantRegId") // Foreign key reference to RoleDetails 
	Tenants tenants;
	

}
