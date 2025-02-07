package com.ourdept.crm_software.crm.modules.processstages.service.interfaces;

import java.util.List;

import com.ourdept.crm_software.crm.modules.processstages.dto.ProcessStageDTO;
import com.ourdept.crm_software.crm.utils.ApiResponse;

public interface ProcessStageService {
    ApiResponse<List<ProcessStageDTO>> createProcessStages(List<ProcessStageDTO> processStageDTOs);
    ApiResponse<ProcessStageDTO> updateProcessStage(Long stageId, ProcessStageDTO processStageDTO);
    ApiResponse<Void> updateProcessStageSequence(List<ProcessStageDTO> processStageDTOs);
    ApiResponse<List<ProcessStageDTO>> getAllProcessStages();
    ApiResponse<List<ProcessStageDTO>> getProcessStageDetails();
}
