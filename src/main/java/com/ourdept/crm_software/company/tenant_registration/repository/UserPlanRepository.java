package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.UserPlan;

public interface UserPlanRepository extends JpaRepository<UserPlan, Integer>{

//Optional<UserPlan> findByUserPlan(String userPlan) ;

Optional<UserPlan> findByMinimumUserAndMaximumUser(int minimumUser, int maximumUser);

}
