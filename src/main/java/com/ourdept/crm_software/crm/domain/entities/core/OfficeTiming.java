package com.ourdept.crm_software.crm.domain.entities.core;

import java.time.LocalTime;
import java.util.List;

import com.ourdept.crm_software.crm.domain.enums.Status;

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
public class OfficeTiming extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "officeTimingId")
	long officeTimingId;
	
	@Column(name = "shiftName")
	String shiftName;// morning / afternoon/ night

	@Column(name = "officeHours")
	int officeHours;// in hrs

	@Column(name = "startTime")
	LocalTime startTime;

	@Column(name = "endTime")
	LocalTime endTime;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	Status status;

	@ManyToMany
	@JoinTable(name = "officeTiming_branches", joinColumns = @JoinColumn(name = "officeTimingId"), inverseJoinColumns = @JoinColumn(name = "branchId"))
	List<Branch> branches;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")  // Foreign key reference to Company
	Company company;

	@OneToMany(mappedBy = "officeTiming")
	List<Employee> employees;

}
