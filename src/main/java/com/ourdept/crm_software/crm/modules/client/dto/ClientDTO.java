package com.ourdept.crm_software.crm.modules.client.dto;


import com.ourdept.crm_software.crm.domain.enums.crm.ClientCategory;
import com.ourdept.crm_software.crm.domain.enums.crm.ClientStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String companyName;
    private String contactPersonName;
    private String email;
    private String whatsAppNumber;
    private String mobileNumber;
    private ClientCategory category;
    private ClientStatus status;
}
