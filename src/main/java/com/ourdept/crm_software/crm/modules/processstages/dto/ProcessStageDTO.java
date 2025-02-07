package com.ourdept.crm_software.crm.modules.processstages.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessStageDTO {
    private Long id;
    private String name;
    private String description;
    private Integer sequenceNumber;
    private Boolean isFinalStage;

    public ProcessStageDTO(Long id, String name, Integer sequenceNumber) {
        this.id = id;
        this.name = name;
        this.sequenceNumber = sequenceNumber;
    }
}
