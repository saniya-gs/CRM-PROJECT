package com.ourdept.crm_software.crm.domain.entities.core;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Company extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "companyId")
	long companyId;

	@Column(name = "companyName")
	String companyName;

	@Column(name = "logoName")
	String logoName;

	@Column(name = "logoType")
	String logoType;

	@Column(name = "logo", columnDefinition = "LONGBLOB")
	byte[] logo;

	@Column(name = "empIDPrefix")
	String empIDPrefix;

//	String modules;

	@Column(name = "ceo")
	String ceo;

	@Column(name = "email")
	String email;

	@OneToMany(mappedBy = "company")
	List<OfficeTiming> officeTimings;

	@OneToMany(mappedBy = "company")
	List<Holidays> holidays;

	@OneToMany(mappedBy = "company")
	List<RoleDetails> roles;

	@OneToMany(mappedBy = "company")
	List<Department> departments;

	@OneToMany(mappedBy = "company")
	List<Branch> branches;

	@OneToOne(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	CompanyAddress address;

	@OneToMany(mappedBy = "company")
	List<WorkingDays> workingDays;

	@OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
	Announcements announcments;

	@OneToMany(mappedBy = "company")
	List<TermsAndConditions> termsAndConditions;

	@OneToMany(mappedBy = "company")
	List<Employee> employees;
	
	@OneToOne(mappedBy = "company")
	CompanySettings companySettings;
	

//	ATTENDANCE
//	
//	@OneToMany(mappedBy = "company")
//	List<AttendanceType> attendanceTypes;
	
	
	
//	LEAVE MANAGEMENT
	
//	@OneToMany(mappedBy = "company")
//	List<LeaveTypes> leaveTypes;
	
	
	
	
	
	

}

