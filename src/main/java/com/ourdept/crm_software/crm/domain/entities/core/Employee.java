package com.ourdept.crm_software.crm.domain.entities.core;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ourdept.crm_software.crm.domain.entities.crm.ProcessHistory;
import com.ourdept.crm_software.crm.domain.enums.BloodGroup;
import com.ourdept.crm_software.crm.domain.enums.EmployementType;
import com.ourdept.crm_software.crm.domain.enums.Gender;
import com.ourdept.crm_software.crm.domain.enums.MaritalStatus;
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
public class Employee extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empId")
	long empId;

	@Column(name = "firstName")
	String firstName;

	@Column(name = "lastName")
	String lastName;
	
	@Column(name = "userID")
	String userID;
	
	@Column(name = "profileImageName")
	String profileImageName;

	@Column(name = "profileImageType")
	String profileImageType;

	@Column(name = "profileImage", columnDefinition = "LONGBLOB")
	byte[] profileImage;

	@Column(name = "email")
	String email;

	@Column(name = "companyEmail")
	String companyEmail;

	@Column(name = "password")
	String password;

	@Column(name = "mobileNo")
	long mobileNo;

	@Column(name = "emergencyContactName")
	String emergencyContactName;

	@Column(name = "emergencyContactNo")
	long emergencyContactNo;

	@Column(name = "dob")
	LocalDate dob;

	@Column(name = "doj")
	LocalDate doj;

	@Column(name = "bloodGroup")
	@Enumerated(EnumType.STRING)
	BloodGroup bloodGroup;//enum	

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	Gender gender;

	@Column(name = "martialStatus")
	@Enumerated(EnumType.STRING)
	MaritalStatus martialStatus;

	@Column(name = "insuranceNo")
	String insuranceNo;

	@Column(name = "employementType")
	@Enumerated(EnumType.STRING)
	EmployementType employementType;// permenant, contract, intern

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "managerId", referencedColumnName = "empId") // Foreign key reference to RoleDetails
	private Employee manager; // If this is the manager field

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hrId", referencedColumnName = "empId") // Foreign key reference to RoleDetails
	private Employee hr;

	@OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	List<Address> address;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId", referencedColumnName = "companyId") // Foreign key reference to RoleDetails
	Company company;

//    Eager
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", referencedColumnName = "roleId") // Foreign key reference to RoleDetails
	RoleDetails roles;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "officeTimingId", referencedColumnName = "officeTimingId") // Foreign key reference to //																					// RoleDetails
	OfficeTiming officeTiming;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", referencedColumnName = "branchId") // Foreign key reference to Branch
	Branch branch;

	@ManyToMany(mappedBy = "employees")
	List<Holidays> holidays;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workingDaysId", referencedColumnName = "workingDaysId") // Foreign key reference to Branch
	WorkingDays workingDays;

	@ManyToMany(mappedBy = "employees")
	List<Department> departments;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_authorities", joinColumns = @JoinColumn(name = "employeeId"), inverseJoinColumns = @JoinColumn(name = "authorityId"))
	private Set<Authorities> authorities = new HashSet<>();

	@OneToMany(mappedBy = "employee")
	List<Certification> certifications;

	@OneToMany(mappedBy = "employee")
	List<Experience> experiences;

	@OneToMany(mappedBy = "employee")
	List<Qualification> qualifications;

	@OneToOne(mappedBy = "employee")
	SocialMedia socialMedias;
	
//	CRM MAPPINGS
	
	@OneToMany(mappedBy = "employeeAssigned")
	List<ProcessHistory> assignedProcessHistories ;
	
	@OneToMany(mappedBy = "returnedByEmployee")
	List<ProcessHistory> returnedProcessHistories ;

}