package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class MasterEmployees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "masterEmpId")
	long masterEmpId;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "phoneNo")
	long phoneNo;
	
	@Column(name = "empId")
	String empId;
	
	@Column(name = "companyEmail")
	String companyEmail;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "role")
	String role;
	
	@Column(name = "designation")
	String designation;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "masterEmployee_products", joinColumns = @JoinColumn(name = "masterEmpId"), inverseJoinColumns = @JoinColumn(name = "productId"))
	List<Products> products;

}
