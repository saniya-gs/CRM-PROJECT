package com.ourdept.crm_software.crm.repository.crm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.crm.Enquiry;
import com.ourdept.crm_software.crm.domain.entities.crm.PurchaseOrder;
import com.ourdept.crm_software.crm.domain.enums.crm.OrderStatus;
import com.ourdept.crm_software.crm.domain.enums.crm.PaymentStatus;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByOrderStatus(OrderStatus orderStatus);
    List<PurchaseOrder> findByPaymentStatus(PaymentStatus paymentStatus);
	boolean existsByEnquiry(Enquiry enquiry);
}
