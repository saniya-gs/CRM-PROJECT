package com.ourdept.crm_software.crm.domain.entities.purchaseplan;

import java.util.List;

import com.ourdept.crm_software.crm.domain.entities.core.BaseEntity;
import com.ourdept.crm_software.crm.domain.entities.core.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class ModulePlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moduleId")
    long moduleId;
    
    String moduleName;
    
    String moduleAccessID;
    
    String description;
    
    @OneToMany(mappedBy = "modulePlan")
	List<ModuleFeatures> moduleFeatures;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaseDetailsId", referencedColumnName = "purchaseDetailsId")  // Foreign key reference to Company
    TenantPurchaseDetails purchaseDetails ;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")  // Foreign key reference to Company
	Company company;
    
}
