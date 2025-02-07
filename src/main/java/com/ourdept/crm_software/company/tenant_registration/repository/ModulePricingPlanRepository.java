	package com.ourdept.crm_software.company.tenant_registration.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.ModulePricingPlan;
import com.ourdept.crm_software.company.tenant_registration.domain.entity.Modules;
import com.ourdept.crm_software.company.tenant_registration.domain.entity.SubscriptionPlan;
import com.ourdept.crm_software.company.tenant_registration.domain.entity.UserPlan;

@Repository
public interface ModulePricingPlanRepository extends JpaRepository<ModulePricingPlan, Integer> {
    // Check if module pricing plan exists for the given module, subscription plan, and user plan for the current date
    Optional<ModulePricingPlan> findByModulesAndSubscriptionPlanAndUserPlanAndPriceAllotedAtBeforeAndPriceAllotedTillAfter(
            Modules modules, SubscriptionPlan subscriptionPlan, UserPlan userPlan, LocalDate start, LocalDate end);
}
