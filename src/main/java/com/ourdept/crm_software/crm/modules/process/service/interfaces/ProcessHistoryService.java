package com.ourdept.crm_software.crm.modules.process.service.interfaces;

import java.util.List;

import com.ourdept.crm_software.crm.modules.process.dto.ProcessHistoryDTO;
import com.ourdept.crm_software.crm.utils.ApiResponse;

public interface ProcessHistoryService {
    ApiResponse<List<ProcessHistoryDTO>> getProcessHistoryByStatusAndEmployee(String status, Long employeeId);
    ApiResponse<List<ProcessHistoryDTO>> getProcessHistoryByPurchaseOrder(Long purchaseOrderId);
    ApiResponse<List<ProcessHistoryDTO>> getProcessHistoryByProcessStage(Long stageId);
    ApiResponse<List<ProcessHistoryDTO>> getProcessHistoryByProcessStageAndEmployee(Long stageId, Long employeeId);
    ApiResponse<Void> updateProcessHistoryAsCompleted(Long processHistoryId, List<String> processMessages);
    ApiResponse<Void> revertProcessHistory(Long processHistoryId, String returnedMessage, long returnedByEmployeeId);
}
