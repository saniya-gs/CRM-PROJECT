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
public class Experience extends  BaseEntity  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "experienceId")
	long experienceId;

	@Column(name = "companyName")
	String companyName;

	@Column(name = "designation")
	String designation;

	@Column(name = "workedFrom")
	LocalDate workedFrom;

	@Column(name = "workedTill")
	LocalDate workedTill;

	@Column(name = "description")
	String description;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", referencedColumnName = "empId")  // Foreign key reference to Employee
	Employee employee;

}

