package com.ourdept.crm_software.crm.repository.crm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.crm.ProcessMessage;

public interface ProcessMessageRepository extends JpaRepository<ProcessMessage, Long> {
}
