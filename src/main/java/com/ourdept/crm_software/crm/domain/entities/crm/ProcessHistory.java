package com.ourdept.crm_software.crm.domain.entities.crm;

import java.util.ArrayList;
import java.util.List;

import com.ourdept.crm_software.crm.domain.entities.core.BaseEntity;
import com.ourdept.crm_software.crm.domain.entities.core.Employee;
import com.ourdept.crm_software.crm.domain.enums.crm.ProcessStatus;

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
public class ProcessHistory extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "processHistoryId")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "processStageId", nullable = false)
	private ProcessStage processStage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "process_history_enquiry", joinColumns = @JoinColumn(name = "processHistoryId"), inverseJoinColumns = @JoinColumn(name = "enquiryId"))
	private List<Enquiry> enquiries;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productOrderHistoryId", nullable = false)
	private ProductOrderHistory productOrderHistory;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ProcessStatus status; // Enum: ASSIGNED, COMPLETED

	@Column(name = "returned", nullable = false)
	private Boolean returned;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "returnedByEmployeeId")
	private Employee returnedByEmployee;

	@Column(name = "returnedMessage")
	private String returnedMessage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeAssignedId")
	private Employee employeeAssigned;

	@OneToMany(mappedBy = "processHistory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProcessMessage> processMessages = new ArrayList<>();
}
