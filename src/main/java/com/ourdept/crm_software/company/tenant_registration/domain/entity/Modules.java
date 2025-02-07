package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.List;

import com.ourdept.crm_software.company.tenant_registration.domain.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Modules {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "moduleId", nullable = false)
	private long moduleId;

	@Column(name = "moduleCode")
	private String moduleCode;

	@Column(name = "moduleName")
	private String moduleName;

	@Column(name = "moduleDescription")
	private String moduleDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId", referencedColumnName = "productId") // Foreign key reference to
	Products products;

	@ManyToMany(mappedBy = "modules")
	List<DiscountPlans> discountPlans;

	@ManyToMany(mappedBy = "modules")
	List<PurchaseRequest> purchaseRequests;

	@OneToMany(mappedBy = "modules")
	List<ModulePricingPlan> modulepricehistories ;

	@ManyToMany(mappedBy = "modules")
	List<PurchaseDetails> purchaseDetails ;

	@OneToMany(mappedBy = "modules", cascade = CascadeType.ALL)
	List<ModuleFeature> modulefeatures ;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	Status status;
	// Getters and Setters
}
