package com.ourdept.crm_software.crm.repository.crm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.crm.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByQuantityAvailableGreaterThan(Integer quantity);
}
