	package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.DiscountPlans;

@Repository
public interface DiscountPlansRepository extends JpaRepository<DiscountPlans, Integer> {

	Optional<DiscountPlans> findByCuponCode(String cuponCode);
}
