package com.ourdept.crm_software.crm.domain.entities.core;

import java.util.List;

import com.ourdept.crm_software.crm.domain.enums.Status;

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
import jakarta.persistence.JoinTable;
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
public class Department extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "departmentId")
	long departmentId;

	@Column(name = "deptName")
	String departmentName;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departmentManagerId")
    private Employee departmentManager;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "workingDaysId")
    private WorkingDays workingDays;
	
	@ManyToMany(mappedBy = "departments")	 
	List<Branch> branches;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	Status status;

	@ManyToMany
	  @JoinTable(
		        name = "departments_employees",
		        joinColumns = @JoinColumn(name = "departmentId"),
		        inverseJoinColumns = @JoinColumn(name = "employeeId")
		    )
	List<Employee> employees;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")  // Foreign key reference to Company
	Company company;

}

