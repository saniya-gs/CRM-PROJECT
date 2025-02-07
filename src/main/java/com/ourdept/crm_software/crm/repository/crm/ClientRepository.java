package com.ourdept.crm_software.crm.repository.crm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.crm.Client;
import com.ourdept.crm_software.crm.domain.enums.crm.ClientStatus;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByCompanyName(String companyName);
    List<Client> findAllByStatus(ClientStatus status);
}
