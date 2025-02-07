package com.ourdept.crm_software.crm.domain.entities.purchaseplan;

import com.ourdept.crm_software.crm.domain.entities.core.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class ModuleFeatures extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moduleFeatureId")
    long moduleFeatureId;
    
    String featureName;
    
    String featureAccessID;   

    @ManyToOne
    @JoinColumn(name = "moduleId", referencedColumnName = "moduleId")  // Foreign key reference to Company
    private ModulePlan modulePlan;
    

}
