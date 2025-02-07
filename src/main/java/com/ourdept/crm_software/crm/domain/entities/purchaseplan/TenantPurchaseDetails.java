package com.ourdept.crm_software.crm.domain.entities.purchaseplan;

import java.time.LocalDate;
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
public class TenantPurchaseDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseDetailsId")
    long purchaseDetailsId;
    
   String transactionId;
   
   LocalDate purchasedAt;
   
   LocalDate validTill;
   
   @OneToMany(mappedBy = "purchaseDetails")
  	List<ModulePlan> modulePlans;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "companyId", referencedColumnName = "companyId")  // Foreign key reference to Company
	Company company;
   
    
	
	

}
