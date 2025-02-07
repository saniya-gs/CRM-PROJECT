package com.ourdept.crm_software.crm.domain.entities.core;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class WorkingDays extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workingDaysId")
	long workingDayId;

	@OneToMany(mappedBy = "workingDays")
	List<Department> departments;

	@ManyToMany
	@JoinTable(name = "workingDays_branches", joinColumns = @JoinColumn(name = "workingDayId"), inverseJoinColumns = @JoinColumn(name = "branchId"))
	List<Branch> branches;

	@Column(name = "workingTypeName")
	String workingTypeName;//

	@Column(name = "monday")
	boolean monday;

	@Column(name = "tuesday")
	boolean tuesday;

	@Column(name = "wednesday")
	boolean wednesday;

	@Column(name = "thursday")
	boolean thursday;

	@Column(name = "friday")
	boolean friday;

	@Column(name = "saturday")
	boolean saturday;

	@Column(name = "sunday")
	boolean sunday;

	@Column(name = "holidayOnFirstSaturday")
	boolean holidayOnFirstSaturday;

	@Column(name = "holidayOnFirstSunday")
	boolean holidayOnFirstSunday;

	@Column(name = "holidayOnSecondSaturday")
	boolean holidayOnSecondSaturday;

	@Column(name = "holidayOnSecondSunday")
	boolean holidayOnSecondSunday;

	@Column(name = "holidayOnThirdSaturday")
	boolean holidayOnThirdSaturday;

	@Column(name = "holidayOnThirdSunday")
	boolean holidayOnThirdSunday;

	@Column(name = "holidayOnFourthSaturday")
	boolean holidayOnFourthSaturday;

	@Column(name = "holidayOnFourthSunday")
	boolean holidayOnFourthSunday;

	@Column(name = "holidayOnFifthSaturday")
	boolean holidayOnFifthSaturday;

	@Column(name = "holidayOnFifthSunday")
	boolean holidayOnFifthSunday;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId", referencedColumnName = "companyId") // Foreign key reference to Company
	Company company;

	@OneToMany(mappedBy = "workingDays")
	List<Employee> employees;

}
