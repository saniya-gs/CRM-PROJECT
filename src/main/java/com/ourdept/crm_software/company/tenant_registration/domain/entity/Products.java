package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "productId")
	long productId; 
	
	@Column(name = "productName")
	String productName; 
	
	@Column(name = "productLink")
	String productLink; 
	
	@Column(name = "description")
	String description;
	
	@OneToMany(mappedBy = "products")
	List<TenantConfigInfo> tenantConfigInfo;
	
	@ManyToMany(mappedBy = "products")
	List<MasterUsers> masterUsers;
	
	@ManyToMany(mappedBy = "products")
	List<MasterEmployees> masterEmployees;
	
	@OneToMany(mappedBy = "products")
	List<Modules> modules;

}
