package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenantauthorities")
public class TenantsAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tenantsAuthorityId")
	private long tenantsAuthorityId;

	@Column(name = "authorityName")
	private String authorityName;

	@ManyToMany(mappedBy = "authorities")
	private Set<Tenants> tenants;

}
