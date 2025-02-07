package com.ourdept.crm_software.crm.modules.client.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCompanyNameDTO {
    private Long id;
    private String companyName;
    private String contactPersonName;
}