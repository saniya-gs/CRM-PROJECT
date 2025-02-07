package com.ourdept.crm_software.company.tenant_registration.domain.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
public class ModuleFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "moduleFeatureId")
	long moduleFeatureId;
	
	@Column(name = "moduleFeatureName")
	String moduleFeatureName;
	
	@Column(name = "FeatureDescription")
	String FeatureDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moduleId", referencedColumnName = "moduleId") // Foreign key reference to
	Modules modules;

	@ManyToMany(mappedBy = "modulefeature")
	List<SubscriptionPlan> mainPlan ;

}