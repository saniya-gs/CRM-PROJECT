package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.SubscriptionPlan;
import com.ourdept.crm_software.company.tenant_registration.domain.enums.Status;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Integer> {
    // Check if subscription plan exists by name
    Optional<SubscriptionPlan> findByPlanName(String planName);

    // Check if subscription plan exists by name excluding the current one (for updates)
    Optional<SubscriptionPlan> findByPlanNameAndSubscriptionPlanIdNot(String planName, int mainPlanId);

    // Fetch all subscription plans with their status
    List<SubscriptionPlan> findByStatus(Status status);
}
