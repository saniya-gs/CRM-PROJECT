package com.ourdept.crm_software.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
