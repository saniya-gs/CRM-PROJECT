package com.ourdept.crm_software.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.TermsAndConditions;

public interface TermsAndConditionRepository extends JpaRepository<TermsAndConditions, Long>{

}
