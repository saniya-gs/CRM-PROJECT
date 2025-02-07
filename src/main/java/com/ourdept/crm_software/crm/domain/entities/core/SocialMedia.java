package com.ourdept.crm_software.crm.domain.entities.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class SocialMedia extends  BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "socialId")
	long socialId;

	@Column(name = "twitterLink")
	String twitterLink;

	@Column(name = "linkdilenLink")
	String linkdilenLink;

	@Column(name = "fbLink")
	String fbLink;

	@Column(name = "instaLink")
	String instaLink;

	@Column(name = "whatssappNo")
	String whatssappNo;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", referencedColumnName = "empId")  // Foreign key reference to Employee
	Employee employee;

}

