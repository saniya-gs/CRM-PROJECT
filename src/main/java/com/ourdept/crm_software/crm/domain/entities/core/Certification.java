package com.ourdept.crm_software.crm.domain.entities.core;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Certification extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "certificationId")
	long certificationId;

	@Column(name = "certificationName")
	String certificationName;

	@Column(name = "description")
	String description;

	@Column(name = "certifiedOn")
	LocalDate certifiedOn;

	@Column(name = "institutionName")
	String institutionName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId", referencedColumnName = "empId") // Foreign key reference to Employee
	Employee employee;

}
