package com.ourdept.crm_software.crm.domain.entities.core;

import java.time.LocalDate;

import com.ourdept.crm_software.crm.domain.enums.QualificationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Qualification extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qualificationId")
	long qualificationId;

	@Column(name = "qualificationType")
	@Enumerated(EnumType.STRING)
	QualificationType qualificionType;//'SCHOOL', 'PRE_UNIVERSITY','BACHELOR', 'MASTER', 'PHD')
	
	@Column(name = "DegreeName")
	String DegreeName;

	@Column(name = "Institution")
	String Institution;

	@Column(name = "percentage")
	double percentage;// CGPA

	@Column(name = "courseStart")
	LocalDate courseStart;

	@Column(name = "courseEnd")
	LocalDate courseEnd;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", referencedColumnName = "empId")  // Foreign key reference to Employee
	Employee employee;

}
