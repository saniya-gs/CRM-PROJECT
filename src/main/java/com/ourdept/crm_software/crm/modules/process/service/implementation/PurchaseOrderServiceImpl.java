package com.ourdept.crm_software.crm.modules.process.service.implementation;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourdept.crm_software.ExceptionHandler.NotFoundException;
import com.ourdept.crm_software.crm.domain.entities.crm.PurchaseOrder;
import com.ourdept.crm_software.crm.domain.enums.crm.OrderStatus;
import com.ourdept.crm_software.crm.domain.enums.crm.PaymentStatus;
import com.ourdept.crm_software.crm.modules.process.dto.PurchaseOrderDTO;
import com.ourdept.crm_software.crm.modules.process.service.interfaces.PurchaseOrderService;
import com.ourdept.crm_software.crm.repository.crm.PurchaseOrderRepository;
import com.ourdept.crm_software.crm.utils.ApiResponse;
import com.ourdept.crm_software.crm.utils.HttpStatusCodes;
import com.ourdept.crm_software.crm.utils.ResponseHandler;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ResponseHandler<List<PurchaseOrderDTO>> listResponseHandler;

    @Autowired
    private ResponseHandler<Void> voidResponseHandler;

    @Override
    public ApiResponse<List<PurchaseOrderDTO>> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        List<PurchaseOrderDTO> response = purchaseOrders.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return listResponseHandler.success(response, "All purchase orders fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<PurchaseOrderDTO>> getAllPurchaseOrdersWithProcessHistories() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        List<PurchaseOrderDTO> response = purchaseOrders.stream()
                .map(this::mapToDTOWithProcessHistories)
                .collect(Collectors.toList());
        return listResponseHandler.success(response, "All purchase orders with process histories fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> updatePaymentStatus(Long purchaseOrderId, String paymentStatus) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId)
                .orElseThrow(() -> new NotFoundException("Purchase order not found"));

        purchaseOrder.setPaymentStatus(PaymentStatus.valueOf(paymentStatus.toUpperCase()));

        if (paymentStatus.equalsIgnoreCase("PAID")) {
            purchaseOrder.setPaidDate(java.time.LocalDate.now());
        }

        purchaseOrderRepository.save(purchaseOrder);
        return voidResponseHandler.success(null, "Payment status updated successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<PurchaseOrderDTO>> getAllCompletedPurchaseOrders() {
        List<PurchaseOrder> completedOrders = purchaseOrderRepository.findByOrderStatus(OrderStatus.COMPLETED);
        List<PurchaseOrderDTO> response = completedOrders.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return listResponseHandler.success(response, "All completed purchase orders fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<PurchaseOrderDTO>> getAllPendingPurchaseOrdersWithProcessHistories() {
        List<PurchaseOrder> pendingOrders = purchaseOrderRepository.findByPaymentStatus(PaymentStatus.PENDING);
        List<PurchaseOrderDTO> response = pendingOrders.stream()
                .map(this::mapToDTOWithProcessHistories)
                .collect(Collectors.toList());
        return listResponseHandler.success(response, "All pending purchase orders with process histories fetched successfully", HttpStatusCodes.OK);
    }

    private PurchaseOrderDTO mapToDTO(PurchaseOrder purchaseOrder) {
        PurchaseOrderDTO dto = new PurchaseOrderDTO();
        dto.setId(purchaseOrder.getId());
        dto.setBillAmount(purchaseOrder.getBillAmount());
        dto.setOrderStatus(purchaseOrder.getOrderStatus().toString());
        dto.setPaymentStatus(purchaseOrder.getPaymentStatus().toString());
        dto.setPaidDate(purchaseOrder.getPaidDate());
        return dto;
    }

    private PurchaseOrderDTO mapToDTOWithProcessHistories(PurchaseOrder purchaseOrder) {
        PurchaseOrderDTO dto = mapToDTO(purchaseOrder);
        dto.setProcessHistories(purchaseOrder.getProcessHistories().stream()
                .map(processHistory -> {
                    String stageName = processHistory.getProcessStage().getName();
                    String status = processHistory.getStatus().toString();
                    return String.format("Stage: %s, Status: %s", stageName, status);
                })
                .collect(Collectors.toList()));
        return dto;
    }
}
