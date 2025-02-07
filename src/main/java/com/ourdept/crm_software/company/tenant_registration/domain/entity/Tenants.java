package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import jakarta.persistence.OneToMany;
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
public class Tenants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenantRegId")
	long tenantRegId;

	@Column(name = "email")
	String email;

	@Column(name = "username")
	String username;

	@Column(name = "password")
	String password;

	@Column(name = "companyName")
	String companyName;

	@Column(updatable = false, name = "signedAt")
	LocalDate signedAt;

	@Column(name = "companyStrength")
	String companyStrength;

	@Column(name = "officialEmail")
	String officialEmail;

	@Column(name = "role")
	String role;

	@OneToOne(fetch = FetchType.LAZY)
	Address address;

	@OneToOne(fetch = FetchType.LAZY)
	TenantConfigInfo tenantConfigInfo;

	@OneToMany(mappedBy = "tenants")
	List<PurchaseDetails> purchaseDetails;
	
	@OneToMany(mappedBy = "tenants")
	List<PurchaseRequest> purchaseRequests; 

	@OneToMany(mappedBy = "tenants", cascade = CascadeType.REMOVE)
	List<ContactDetails> contactDetails = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tenants_authorities", joinColumns = @JoinColumn(name = "tenantRegId"), inverseJoinColumns = @JoinColumn(name = "tenantsAuthorityId"))
	private Set<TenantsAuthority> authorities;
}
