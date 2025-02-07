package com.ourdept.crm_software.crm.modules.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ourdept.crm_software.crm.modules.client.dto.ClientCompanyNameDTO;
import com.ourdept.crm_software.crm.modules.client.dto.ClientDTO;
import com.ourdept.crm_software.crm.modules.client.service.interfaces.ClientService;
import com.ourdept.crm_software.crm.utils.ApiResponse;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClientDTO>> createClient(@RequestBody ClientDTO clientDTO) {
        ApiResponse<ClientDTO> response = clientService.createClient(clientDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/bulk-upload")
    public ResponseEntity<ApiResponse<Void>> bulkUploadClients(@RequestParam("file") MultipartFile file) {
        ApiResponse<Void> response = clientService.bulkUploadClients(file);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientDTO>> updateClient(@PathVariable("clientId") Long clientId, @RequestBody ClientDTO clientDTO) {
        ApiResponse<ClientDTO> response = clientService.updateClient(clientId, clientDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable("clientId") Long clientId) {
        ApiResponse<Void> response = clientService.deleteClient(clientId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientDTO>>> getAllClients() {
        ApiResponse<List<ClientDTO>> response = clientService.getAllClients();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/status/AVAILABLE")
    public ResponseEntity<ApiResponse<List<ClientDTO>>> getAllAvailableClients() {
        ApiResponse<List<ClientDTO>> response = clientService.getAllClientsByStatus();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientDTO>> getClientById(@PathVariable("clientId") Long clientId) {
        ApiResponse<ClientDTO> response = clientService.getClientById(clientId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/company/{clientId}")
    public ResponseEntity<ApiResponse<List<ClientCompanyNameDTO>>> getClientCompanyAndContactPerson(@PathVariable("clientId") Long clientId) {
        ApiResponse<List<ClientCompanyNameDTO>> response = clientService.getClientCompanyAndContactPerson(clientId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
