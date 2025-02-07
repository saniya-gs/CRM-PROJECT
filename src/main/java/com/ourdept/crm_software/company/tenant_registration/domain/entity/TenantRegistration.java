package com.ourdept.crm_software.company.tenant_registration.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class TenantRegistration{
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "tenantRegistrationId")
	long tenantRegistrationId; 
	
	@Column(name = "username")
	String username; 
	
	@Column(name = "password")
	String password;
	
}
