package com.ourdept.crm_software.crm.domain.entities.crm;

import java.util.ArrayList;
import java.util.List;

import com.ourdept.crm_software.crm.domain.entities.core.BaseEntity;
import com.ourdept.crm_software.crm.domain.enums.crm.KYCStatus;
import com.ourdept.crm_software.crm.domain.enums.crm.TermsStatus;

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
import jakarta.persistence.Lob;
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
public class KYC extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kycId")
	private Long id;

	@OneToMany(mappedBy = "kyc", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TermsAndCondition> termsAndConditions = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@Column(name = "termsStatus", nullable = false)
	private TermsStatus termsStatus; // Enum: ACCEPT, REJECT, PENDING

	@Lob
	@Column(name = "signature")
	private byte[] signature;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private KYCStatus status; // Enum: ACTIVE, INACTIVE

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientId", nullable = false)
	private Client client;
}
