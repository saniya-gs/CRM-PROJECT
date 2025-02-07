package com.ourdept.crm_software.crm.domain.entities.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Announcements extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "announcementsId")
	long announcementsId;
	
	@Column(name = "vision")
	String vision;
	
	@Column(name = "mission")
	String mission;
	
	@Column(name = "coreValues")
	String coreValues;
	
	@Column(name = "goals")
	String goals;
	
	@Column(name = "email")
	String email;//updated by
	
	
	@OneToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")  // Foreign key reference to Company
	Company company;
	
}
 