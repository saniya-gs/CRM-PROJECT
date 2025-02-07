package com.ourdept.crm_software.crm.modules.processstages.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ourdept.crm_software.crm.modules.processstages.dto.ProcessStageDTO;
import com.ourdept.crm_software.crm.modules.processstages.service.interfaces.ProcessStageService;
import com.ourdept.crm_software.crm.utils.ApiResponse;

@RestController
@RequestMapping("/api/process-stages")
public class ProcessStageController {

    @Autowired
    private ProcessStageService processStageService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<ProcessStageDTO>>> createProcessStages(@RequestBody List<ProcessStageDTO> processStageDTOs) {
        ApiResponse<List<ProcessStageDTO>> response = processStageService.createProcessStages(processStageDTOs);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{stageId}")
    public ResponseEntity<ApiResponse<ProcessStageDTO>> updateProcessStage(
            @PathVariable("stageId") Long stageId,
            @RequestBody ProcessStageDTO processStageDTO) {
        ApiResponse<ProcessStageDTO> response = processStageService.updateProcessStage(stageId, processStageDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/sequence")
    public ResponseEntity<ApiResponse<Void>> updateProcessStageSequence(@RequestBody List<ProcessStageDTO> processStageDTOs) {
        ApiResponse<Void> response = processStageService.updateProcessStageSequence(processStageDTOs);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProcessStageDTO>>> getAllProcessStages() {
        ApiResponse<List<ProcessStageDTO>> response = processStageService.getAllProcessStages();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse<List<ProcessStageDTO>>> getProcessStageDetails() {
        ApiResponse<List<ProcessStageDTO>> response = processStageService.getProcessStageDetails();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
