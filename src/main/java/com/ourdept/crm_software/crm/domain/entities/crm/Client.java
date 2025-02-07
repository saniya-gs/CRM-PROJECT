package com.ourdept.crm_software.crm.domain.entities.crm;

import java.util.ArrayList;
import java.util.List;

import com.ourdept.crm_software.crm.domain.entities.core.BaseEntity;
import com.ourdept.crm_software.crm.domain.enums.crm.ClientCategory;
import com.ourdept.crm_software.crm.domain.enums.crm.ClientStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Client extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private Long id;

    @Column(name = "companyName", nullable = false)
    private String companyName;

    @Column(name = "contactPersonName", nullable = false)
    private String contactPersonName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "whatsAppNumber")
    private String whatsAppNumber;

    @Column(name = "mobileNumber", nullable = false)
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ClientCategory category; // Enum: CASH or CREDIT

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ClientStatus status; // Enum: AVAILABLE or OFFLINE

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KYC> kycDetails = new ArrayList<>();
}
