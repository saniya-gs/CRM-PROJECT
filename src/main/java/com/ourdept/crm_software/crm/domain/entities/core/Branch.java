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
public class Branch extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branchId")
	long branchId;

	@Column(name = "branchName")
	String branchName;

	@Column(name = "branchState")
	String branchState;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	Status status;

	@ManyToMany
	@JoinTable(name = "branches_departments", joinColumns = @JoinColumn(name = "branchId"), inverseJoinColumns = @JoinColumn(name = "departmentId"))
	List<Department> departments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId", referencedColumnName = "companyId") // Foreign key reference to Company
	Company company;

	@OneToMany(mappedBy = "branch")
	List<Employee> employees;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	@JoinColumn(name = "branchAddressId") // Foreign key reference to BranchAddress
	BranchAddress branchAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchManagerId", referencedColumnName = "empId")
	private Employee branchManager;

	@ManyToMany(mappedBy = "branches")
	private List<WorkingDays> workingDays;

	@ManyToMany(mappedBy = "branches")
	private List<OfficeTiming> officeTiming;

	@ManyToMany(mappedBy = "branches")
	List<Holidays> holidays;


}
