package com.ourdept.crm_software.crm.domain.entities.core;

import java.time.LocalDate;
import java.util.List;

import com.ourdept.crm_software.crm.domain.enums.HolidayType;

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
public class Holidays extends  BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "holidayId")
	long holidayId;

	@Column(name = "holidayName")
	String holidayName;

	@Column(name = "eventDate")
	LocalDate eventDate;

	@Column(name = "noOfdays")
	int noOfdays;
	

	@ManyToMany(fetch = FetchType.LAZY)
	 @JoinTable(
		        name = "holidays_branches",
		        joinColumns = @JoinColumn(name = "holidayId"),
		        inverseJoinColumns = @JoinColumn(name = "branchId")
		    )
	List<Branch> branches;

	@ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")  // Foreign key reference to Company
	Company company;

	@ManyToMany(fetch = FetchType.LAZY)
	 @JoinTable(
		        name = "holidayList_employees",
		        joinColumns = @JoinColumn(name = "holidayListId"),
		        inverseJoinColumns = @JoinColumn(name = "employeeId")
		    )
	List<Employee> employees;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "holidayType")
	HolidayType holidayType;

}
