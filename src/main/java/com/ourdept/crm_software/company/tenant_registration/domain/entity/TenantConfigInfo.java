package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class TenantConfigInfo {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "tenantConfigId")
	long tenantConfigId; 
	
	@Column(name = "tenantId")
	String tenantId; 
	
	@Column(name = "subDomainUrl")
	String subDomainUrl; 
	
	@Column(name = "purchasePlan")
	String purchasePlan; 
	
	@Column(name = "companyStatus")
	String companyStatus; 
	
	@Column(name = "dbUrl")
	String dbUrl; 
	
	@Column(name = "dbName")
	String dbName;
	
	@Column(name = "dbUsername")
	String dbUsername; 
	
	@Column(name = "dbPassword")
	String dbPassword; 
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId", referencedColumnName = "productId") // Foreign key reference to
	Products products;
	
	@OneToOne(mappedBy = "tenantConfigInfo")
	Tenants tenants;
	
	

}
