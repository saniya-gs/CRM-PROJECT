package com.ourdept.crm_software.crm.modules.client.service.interfaces;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ourdept.crm_software.crm.modules.client.dto.ClientCompanyNameDTO;
import com.ourdept.crm_software.crm.modules.client.dto.ClientDTO;
import com.ourdept.crm_software.crm.utils.ApiResponse;

public interface ClientService {
    ApiResponse<ClientDTO> createClient(ClientDTO clientDTO);
    ApiResponse<Void> bulkUploadClients(MultipartFile file);
    ApiResponse<ClientDTO> updateClient(Long clientId, ClientDTO clientDTO);
    ApiResponse<Void> deleteClient(Long clientId);
    ApiResponse<List<ClientDTO>> getAllClients();
    ApiResponse<List<ClientDTO>> getAllClientsByStatus();
    ApiResponse<ClientDTO> getClientById(Long clientId);
    ApiResponse<List<ClientCompanyNameDTO>> getClientCompanyAndContactPerson(Long clientId);
}
