package com.ourdept.crm_software.crm.domain.entities.core;

import com.ourdept.crm_software.crm.domain.enums.TermsAndConditionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class TermsAndConditions extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "termsAndConsId")
    private long termsAndConsId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TermsAndConditionType type; // Terms/condition


    @Column(name = "info", columnDefinition = "LONGTEXT")
    private String info;


    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")  // Foreign key reference to Company
    private Company company;

}