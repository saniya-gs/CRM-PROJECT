package com.ourdept.crm_software.crm.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementsResponseDTO {
    private long announcementsId;
    private String vision;
    private String mission;
    private String coreValues;
    private String goals;
    private String email;
    private String companyName;
}
