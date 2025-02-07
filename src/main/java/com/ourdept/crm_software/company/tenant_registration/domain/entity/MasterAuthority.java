package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "masterauthorities")
public class MasterAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "masterAuthorityId")
	private long masterAuthorityId;

	@Column(name = "authorityName")
	private String authorityName;

	@ManyToMany(mappedBy = "authorities")
	private Set<MasterUsers> masterUsers= new HashSet<>();

}