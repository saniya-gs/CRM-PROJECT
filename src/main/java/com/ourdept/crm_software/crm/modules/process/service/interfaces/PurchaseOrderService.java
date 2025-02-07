package com.ourdept.crm_software.crm.modules.process.service.interfaces;

import java.util.List;

import com.ourdept.crm_software.crm.modules.process.dto.PurchaseOrderDTO;
import com.ourdept.crm_software.crm.utils.ApiResponse;

public interface PurchaseOrderService {
    ApiResponse<List<PurchaseOrderDTO>> getAllPurchaseOrders();
    ApiResponse<List<PurchaseOrderDTO>> getAllPurchaseOrdersWithProcessHistories();
    ApiResponse<Void> updatePaymentStatus(Long purchaseOrderId, String paymentStatus);
    ApiResponse<List<PurchaseOrderDTO>> getAllCompletedPurchaseOrders();
    ApiResponse<List<PurchaseOrderDTO>> getAllPendingPurchaseOrdersWithProcessHistories();
}
