package com.ourdept.crm_software.company.tenant_registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.company.tenant_registration.domain.entity.ModuleFeature;
import com.ourdept.crm_software.company.tenant_registration.domain.entity.Modules;
import com.ourdept.crm_software.company.tenant_registration.domain.entity.SubscriptionPlan;

public interface ModuleFeaturesRepository extends JpaRepository<ModuleFeature, Integer>{


	List<ModuleFeature> findAllByMainPlan(SubscriptionPlan mainPlan);

    List<ModuleFeature> findAllByModuleFeatureIdInAndModules(List<Integer> moduleFeatureIds, Modules module);


	List<ModuleFeature> findAllByMainPlanAndModules(SubscriptionPlan mainPlan, Modules module);

}
