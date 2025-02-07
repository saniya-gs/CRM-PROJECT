package com.ourdept.crm_software.crm.modules.process.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ourdept.crm_software.crm.modules.process.dto.PurchaseOrderDTO;
import com.ourdept.crm_software.crm.modules.process.service.interfaces.PurchaseOrderService;
import com.ourdept.crm_software.crm.utils.ApiResponse;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PurchaseOrderDTO>>> getAllPurchaseOrders() {
        ApiResponse<List<PurchaseOrderDTO>> response = purchaseOrderService.getAllPurchaseOrders();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/with-process-histories")
    public ResponseEntity<ApiResponse<List<PurchaseOrderDTO>>> getAllPurchaseOrdersWithProcessHistories() {
        ApiResponse<List<PurchaseOrderDTO>> response = purchaseOrderService.getAllPurchaseOrdersWithProcessHistories();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{purchaseOrderId}/payment-status")
    public ResponseEntity<ApiResponse<Void>> updatePaymentStatus(
            @PathVariable("purchaseOrderId") Long purchaseOrderId,
            @RequestParam("paymentStatus") String paymentStatus) {
        ApiResponse<Void> response = purchaseOrderService.updatePaymentStatus(purchaseOrderId, paymentStatus);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/completed")
    public ResponseEntity<ApiResponse<List<PurchaseOrderDTO>>> getAllCompletedPurchaseOrders() {
        ApiResponse<List<PurchaseOrderDTO>> response = purchaseOrderService.getAllCompletedPurchaseOrders();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/pending-with-process-histories")
    public ResponseEntity<ApiResponse<List<PurchaseOrderDTO>>> getAllPendingPurchaseOrdersWithProcessHistories() {
        ApiResponse<List<PurchaseOrderDTO>> response = purchaseOrderService.getAllPendingPurchaseOrdersWithProcessHistories();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
