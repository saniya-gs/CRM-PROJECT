package com.ourdept.crm_software.crm.modules.client.service.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ourdept.crm_software.ExceptionHandler.NotFoundException;
import com.ourdept.crm_software.crm.domain.entities.crm.Client;
import com.ourdept.crm_software.crm.domain.enums.crm.ClientCategory;
import com.ourdept.crm_software.crm.domain.enums.crm.ClientStatus;
import com.ourdept.crm_software.crm.modules.client.dto.ClientCompanyNameDTO;
import com.ourdept.crm_software.crm.modules.client.dto.ClientDTO;
import com.ourdept.crm_software.crm.modules.client.service.interfaces.ClientService;
import com.ourdept.crm_software.crm.repository.crm.ClientRepository;
import com.ourdept.crm_software.crm.utils.ApiResponse;
import com.ourdept.crm_software.crm.utils.HttpStatusCodes;
import com.ourdept.crm_software.crm.utils.ResponseHandler;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ResponseHandler<ClientDTO> responseHandler;

    @Autowired
    private ResponseHandler<List<ClientDTO>> listResponseHandler;

    @Autowired
    private ResponseHandler<List<ClientCompanyNameDTO>> companyNameDTOlistResponseHandler;

    
    @Autowired
    private ResponseHandler<Void> voidResponseHandler;

    @Override
    public ApiResponse<ClientDTO> createClient(ClientDTO clientDTO) {
        // Check if client already exists by company name
        if (clientRepository.existsByCompanyName(clientDTO.getCompanyName())) {
            return responseHandler.error("Client with company name already exists", HttpStatus.CONFLICT.value());
        }

        Client client = mapToEntity(clientDTO);
        Client savedClient = clientRepository.save(client);

        return responseHandler.success(mapToDTO(savedClient), "Client created successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> bulkUploadClients(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);
            List<Client> clients = parser.getRecords().stream()
                    .map(this::mapCsvRecordToEntity)
                    .filter(client -> !clientRepository.existsByCompanyName(client.getCompanyName()))
                    .collect(Collectors.toList());

            clientRepository.saveAll(clients);

            return voidResponseHandler.success(null, "Bulk upload successful", HttpStatusCodes.OK);
        } catch (IOException e) {
            return voidResponseHandler.error("Error reading CSV file", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<ClientDTO> updateClient(Long clientId, ClientDTO clientDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        client.setCompanyName(clientDTO.getCompanyName());
        client.setContactPersonName(clientDTO.getContactPersonName());
        client.setEmail(clientDTO.getEmail());
        client.setMobileNumber(clientDTO.getMobileNumber());
        client.setWhatsAppNumber(clientDTO.getWhatsAppNumber());
        client.setCategory(clientDTO.getCategory());
        client.setStatus(clientDTO.getStatus());

        Client updatedClient = clientRepository.save(client);
        return responseHandler.success(mapToDTO(updatedClient), "Client updated successfully",  HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        clientRepository.delete(client);
        return voidResponseHandler.success(null, "Client deleted successfully",  HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> response = clients.stream().map(this::mapToDTO).collect(Collectors.toList());
        return listResponseHandler.success(response, "All clients fetched successfully",  HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ClientDTO>> getAllClientsByStatus() {
        List<Client> clients = clientRepository.findAllByStatus(ClientStatus.AVAILABLE);
        List<ClientDTO> response = clients.stream().map(this::mapToDTO).collect(Collectors.toList());
        return listResponseHandler.success(response, "All AVAILABLE clients fetched successfully",  HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<ClientDTO> getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("Client not found"));

        return responseHandler.success(mapToDTO(client), "Client fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ClientCompanyNameDTO>> getClientCompanyAndContactPerson(Long clientId) {
        List<Client> clients = clientRepository.findAllByStatus(ClientStatus.AVAILABLE);

        List<ClientCompanyNameDTO> response = clients.stream().map(this::mapToClientNameDTO).collect(Collectors.toList());
        return companyNameDTOlistResponseHandler.success(response, "All AVAILABLE clients fetched successfully",  HttpStatusCodes.OK);
    }

    private Client mapToEntity(ClientDTO dto) {
        Client client = new Client();
        client.setCompanyName(dto.getCompanyName());
        client.setContactPersonName(dto.getContactPersonName());
        client.setEmail(dto.getEmail());
        client.setMobileNumber(dto.getMobileNumber());
        client.setWhatsAppNumber(dto.getWhatsAppNumber());
        client.setCategory(dto.getCategory());
        client.setStatus(dto.getStatus());
        return client;
    }

    private ClientDTO mapToDTO(Client client) {
        return new ClientDTO(client.getId(), client.getCompanyName(), client.getContactPersonName(),
                client.getEmail(), client.getWhatsAppNumber(), client.getMobileNumber(),
                client.getCategory(), client.getStatus());
    }
    
    private ClientCompanyNameDTO mapToClientNameDTO(Client client) {
        return new ClientCompanyNameDTO(client.getId(), client.getCompanyName(), client.getContactPersonName());
    }

	private Client mapCsvRecordToEntity(CSVRecord record) {
        Client client = new Client();
        client.setCompanyName(record.get("companyName"));
        client.setContactPersonName(record.get("contactPersonName"));
        client.setEmail(record.get("email"));
        client.setMobileNumber(record.get("mobileNumber"));
        client.setWhatsAppNumber(record.get("whatsAppNumber"));
        client.setCategory(ClientCategory.valueOf(record.get("category").toUpperCase()));
        client.setStatus(ClientStatus.valueOf(record.get("status").toUpperCase()));
        return client;
    }
}
