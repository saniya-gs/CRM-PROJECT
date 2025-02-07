package com.ourdept.crm_software.crm.modules.process.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourdept.crm_software.ExceptionHandler.NotFoundException;
import com.ourdept.crm_software.company.security.Service.UserContextService;
import com.ourdept.crm_software.crm.domain.entities.core.Employee;
import com.ourdept.crm_software.crm.domain.entities.crm.ProcessHistory;
import com.ourdept.crm_software.crm.domain.entities.crm.ProcessMessage;
import com.ourdept.crm_software.crm.domain.entities.crm.ProcessStage;
import com.ourdept.crm_software.crm.domain.enums.crm.ProcessStatus;
import com.ourdept.crm_software.crm.modules.process.dto.ProcessHistoryDTO;
import com.ourdept.crm_software.crm.modules.process.service.interfaces.ProcessHistoryService;
import com.ourdept.crm_software.crm.repository.crm.ProcessHistoryRepository;
import com.ourdept.crm_software.crm.repository.crm.ProcessMessageRepository;
import com.ourdept.crm_software.crm.repository.crm.ProcessStageRepository;
import com.ourdept.crm_software.crm.utils.ApiResponse;
import com.ourdept.crm_software.crm.utils.HttpStatusCodes;
import com.ourdept.crm_software.crm.utils.ResponseHandler;

@Service
public class ProcessHistoryServiceImpl implements ProcessHistoryService {

    @Autowired
    private ProcessHistoryRepository processHistoryRepository;
    
    @Autowired
    private UserContextService contextService;

    @Autowired
    private ProcessMessageRepository processMessageRepository;

    @Autowired
    private ProcessStageRepository processStageRepository;

    @Autowired
    private ResponseHandler<List<ProcessHistoryDTO>> listResponseHandler;

    @Autowired
    private ResponseHandler<Void> voidResponseHandler;

    @Override
    public ApiResponse<List<ProcessHistoryDTO>> getProcessHistoryByStatusAndEmployee(String status, Long employeeId) {
        ProcessStatus processStatus = ProcessStatus.valueOf(status.toUpperCase());
        List<ProcessHistory> histories = processHistoryRepository.findByStatusAndEmployeeAssigned_EmpId(processStatus, employeeId);
        return listResponseHandler.success(histories.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "Process histories fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ProcessHistoryDTO>> getProcessHistoryByPurchaseOrder(Long purchaseOrderId) {
        List<ProcessHistory> histories = processHistoryRepository.findByProductOrderHistory_PurchaseOrder_Id(purchaseOrderId);
        return listResponseHandler.success(histories.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "Process histories fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ProcessHistoryDTO>> getProcessHistoryByProcessStage(Long stageId) {
        List<ProcessHistory> histories = processHistoryRepository.findByProcessStage_Id(stageId);
        return listResponseHandler.success(histories.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "Process histories fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ProcessHistoryDTO>> getProcessHistoryByProcessStageAndEmployee(Long stageId, Long employeeId) {
        List<ProcessHistory> histories = processHistoryRepository.findByProcessStage_IdAndEmployeeAssigned_EmpId(stageId, employeeId);
        return listResponseHandler.success(histories.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "Process histories fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> updateProcessHistoryAsCompleted(Long processHistoryId, List<String> processMessages) {
        ProcessHistory history = processHistoryRepository.findById(processHistoryId)
                .orElseThrow(() -> new NotFoundException("Process history not found"));

        // Add process messages
        for (String message : processMessages) {
            ProcessMessage processMessage = new ProcessMessage();
            processMessage.setMessage(message);
            processMessage.setProcessHistory(history);
            processMessageRepository.save(processMessage);
        }

        // Update status to COMPLETED
        history.setStatus(ProcessStatus.COMPLETED);
        processHistoryRepository.save(history);

        // Create new process history if not the final stage
        ProcessStage currentStage = history.getProcessStage();
        if (!currentStage.getIsFinalStage()) {
            ProcessStage nextStage = processStageRepository.findBySequenceNumberAndDepartment_DepartmentId(
                    currentStage.getSequenceNumber() + 1, currentStage.getDepartment().getDepartmentId())
                    .orElseThrow(() -> new NotFoundException("Next process stage not found"));

            ProcessHistory nextHistory = new ProcessHistory();
            nextHistory.setProcessStage(nextStage);
            nextHistory.setProduct(history.getProduct());
            nextHistory.setProductOrderHistory(history.getProductOrderHistory());
            nextHistory.setStatus(ProcessStatus.ASSIGNED);
            nextHistory.setReturned(false);
            processHistoryRepository.save(nextHistory);
        }

        return voidResponseHandler.success(null, "Process history updated successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> revertProcessHistory(Long processHistoryId, String returnedMessage, long returnedByEmployeeId) {
        ProcessHistory history = processHistoryRepository.findById(processHistoryId)
                .orElseThrow(() -> new NotFoundException("Process history not found"));
        
     Employee employee =   contextService.getEmployeeById(returnedByEmployeeId);

        history.setReturned(true);
        history.setReturnedMessage(returnedMessage);
        
        history.setReturnedByEmployee(employee); // Assuming Employee is managed by ID
        history.setStatus(ProcessStatus.RETURNED);
        processHistoryRepository.save(history);

        return voidResponseHandler.success(null, "Process history reverted successfully", HttpStatusCodes.OK);
    }

    private ProcessHistoryDTO mapToDTO(ProcessHistory history) {
        // Map ProcessHistory to ProcessHistoryDTO
        ProcessHistoryDTO dto = new ProcessHistoryDTO();
        dto.setId(history.getId());
        dto.setStatus(history.getStatus().toString());
        dto.setProcessStageName(history.getProcessStage().getName());
        dto.setProductName(history.getProduct().getName());
        dto.setReturned(history.getReturned());
        dto.setEmployeeAssignedName(history.getEmployeeAssigned() != null ? history.getEmployeeAssigned().getEmergencyContactName() : null);
        return dto;
    }
}
