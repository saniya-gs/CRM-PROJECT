package com.ourdept.crm_software.crm.modules.processstages.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourdept.crm_software.ExceptionHandler.NotFoundException;
import com.ourdept.crm_software.crm.domain.entities.crm.ProcessStage;
import com.ourdept.crm_software.crm.modules.processstages.dto.ProcessStageDTO;
import com.ourdept.crm_software.crm.modules.processstages.service.interfaces.ProcessStageService;
import com.ourdept.crm_software.crm.repository.crm.ProcessStageRepository;
import com.ourdept.crm_software.crm.utils.ApiResponse;
import com.ourdept.crm_software.crm.utils.HttpStatusCodes;
import com.ourdept.crm_software.crm.utils.ResponseHandler;

@Service
public class ProcessStageServiceImpl implements ProcessStageService {

    @Autowired
    private ProcessStageRepository processStageRepository;

    @Autowired
    private ResponseHandler<ProcessStageDTO> responseHandler;

    @Autowired
    private ResponseHandler<List<ProcessStageDTO>> listResponseHandler;

    @Autowired
    private ResponseHandler<Void> voidResponseHandler;

    @Override
    public ApiResponse<List<ProcessStageDTO>> createProcessStages(List<ProcessStageDTO> processStageDTOs) {
        List<ProcessStage> stages = processStageDTOs.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        List<ProcessStage> savedStages = processStageRepository.saveAll(stages);

        return listResponseHandler.success(
                savedStages.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "Process stages created successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<ProcessStageDTO> updateProcessStage(Long stageId, ProcessStageDTO processStageDTO) {
        ProcessStage stage = processStageRepository.findById(stageId)
                .orElseThrow(() -> new NotFoundException("Process stage not found"));

        stage.setName(processStageDTO.getName());
        stage.setDescription(processStageDTO.getDescription());
        stage.setIsFinalStage(processStageDTO.getIsFinalStage());

        ProcessStage updatedStage = processStageRepository.save(stage);

        return responseHandler.success(mapToDTO(updatedStage), "Process stage updated successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> updateProcessStageSequence(List<ProcessStageDTO> processStageDTOs) {
        for (ProcessStageDTO dto : processStageDTOs) {
            ProcessStage stage = processStageRepository.findById(dto.getId())
                    .orElseThrow(() -> new NotFoundException("Process stage not found"));

            stage.setSequenceNumber(dto.getSequenceNumber());
            processStageRepository.save(stage);
        }
        return voidResponseHandler.success(null, "Process stage sequences updated successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ProcessStageDTO>> getAllProcessStages() {
        List<ProcessStage> stages = processStageRepository.findAll();
        return listResponseHandler.success(
                stages.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "All process stages fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ProcessStageDTO>> getProcessStageDetails() {
        List<ProcessStage> stages = processStageRepository.findAll();
        return listResponseHandler.success(
                stages.stream()
                        .map(stage -> new ProcessStageDTO(stage.getId(), stage.getName(), stage.getSequenceNumber()))
                        .collect(Collectors.toList()),
                "Process stage details fetched successfully", HttpStatusCodes.OK);
    }

    private ProcessStage mapToEntity(ProcessStageDTO dto) {
        ProcessStage stage = new ProcessStage();
        stage.setName(dto.getName());
        stage.setDescription(dto.getDescription());
        stage.setSequenceNumber(dto.getSequenceNumber());
        stage.setIsFinalStage(dto.getIsFinalStage());
        return stage;
    }

    private ProcessStageDTO mapToDTO(ProcessStage stage) {
        ProcessStageDTO dto = new ProcessStageDTO();
        dto.setId(stage.getId());
        dto.setName(stage.getName());
        dto.setDescription(stage.getDescription());
        dto.setSequenceNumber(stage.getSequenceNumber());
        dto.setIsFinalStage(stage.getIsFinalStage());
        return dto;
    }
}
