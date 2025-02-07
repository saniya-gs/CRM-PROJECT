package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ourdept.crm_software.company.tenant_registration.domain.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

//founder  details
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MasterUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "masterId")
	long masterId; 
	
	@Column(name = "userName")
	String userName; 
	
	@Column(name = "email")
	String email; 
	
	@Column(name = "password")
	String password; 
	
	@Column(name = "phoneNo")
	long phoneNo; 	
	
	@Column(name = "designation")
	String designation;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	Status status;
	
	@ManyToMany
	@JoinTable(name = "masterUser_products", joinColumns = @JoinColumn(name = "masterId"), inverseJoinColumns = @JoinColumn(name = "productId"))
	List<Products> products ;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "masterUser_masterAuthority", joinColumns = @JoinColumn(name = "masterId"), inverseJoinColumns = @JoinColumn(name = "masterAuthorityId"))
	private Set<MasterAuthority> authorities= new HashSet<>();
	
}
