package com.ourdept.crm_software.crm.modules.process.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ourdept.crm_software.crm.modules.process.dto.ProcessHistoryDTO;
import com.ourdept.crm_software.crm.modules.process.service.interfaces.ProcessHistoryService;
import com.ourdept.crm_software.crm.utils.ApiResponse;

@RestController
@RequestMapping("/api/process-history")
public class ProcessHistoryController {

    @Autowired
    private ProcessHistoryService processHistoryService;

    @GetMapping("/status/{status}/employee/{employeeId}")
    public ResponseEntity<ApiResponse<List<ProcessHistoryDTO>>> getProcessHistoryByStatusAndEmployee(
            @PathVariable("status") String status, @PathVariable("employeeId") Long employeeId) {
        ApiResponse<List<ProcessHistoryDTO>> response = processHistoryService.getProcessHistoryByStatusAndEmployee(status, employeeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/purchase-order/{purchaseOrderId}")
    public ResponseEntity<ApiResponse<List<ProcessHistoryDTO>>> getProcessHistoryByPurchaseOrder(
            @PathVariable("purchaseOrderId") Long purchaseOrderId) {
        ApiResponse<List<ProcessHistoryDTO>> response = processHistoryService.getProcessHistoryByPurchaseOrder(purchaseOrderId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/process-stage/{stageId}")
    public ResponseEntity<ApiResponse<List<ProcessHistoryDTO>>> getProcessHistoryByProcessStage(
            @PathVariable("stageId") Long stageId) {
        ApiResponse<List<ProcessHistoryDTO>> response = processHistoryService.getProcessHistoryByProcessStage(stageId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/process-stage/{stageId}/employee/{employeeId}")
    public ResponseEntity<ApiResponse<List<ProcessHistoryDTO>>> getProcessHistoryByProcessStageAndEmployee(
            @PathVariable("stageId") Long stageId, @PathVariable("employeeId") Long employeeId) {
        ApiResponse<List<ProcessHistoryDTO>> response = processHistoryService.getProcessHistoryByProcessStageAndEmployee(stageId, employeeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{processHistoryId}/complete")
    public ResponseEntity<ApiResponse<Void>> updateProcessHistoryAsCompleted(
            @PathVariable("processHistoryId") Long processHistoryId,
            @RequestBody List<String> processMessages) {
        ApiResponse<Void> response = processHistoryService.updateProcessHistoryAsCompleted(processHistoryId, processMessages);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{processHistoryId}/revert")
    public ResponseEntity<ApiResponse<Void>> revertProcessHistory(
            @PathVariable("processHistoryId") Long processHistoryId,
            @RequestParam("returnedMessage") String returnedMessage,
            @RequestParam("returnedByEmployeeId") Long returnedByEmployeeId) {
        ApiResponse<Void> response = processHistoryService.revertProcessHistory(processHistoryId, returnedMessage, returnedByEmployeeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
