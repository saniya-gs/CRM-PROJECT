package com.ourdept.crm_software.crm.repository.plans;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.crm.domain.entities.purchaseplan.TenantPurchaseDetails;

@Repository
public interface TenantPurchaseDetailsRepository extends JpaRepository<TenantPurchaseDetails, Long> {

	@Query("SELECT t FROM TenantPurchaseDetails t "
			+ "WHERE t.purchasedAt <= :currentDate AND t.validTill >= :currentDate "
			+ "AND t.company.companyId = :companyId")
	List<TenantPurchaseDetails> findActivePurchaseDetails(@Param("currentDate") LocalDate currentDate,
			@Param("companyId") long companyId);
}
