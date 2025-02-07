package com.ourdept.crm_software.crm.modules.enquiry.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.ourdept.crm_software.crm.modules.enquiry.dto.EnquiryDTO;
import com.ourdept.crm_software.crm.modules.enquiry.service.interfaces.EnquiryService;
import com.ourdept.crm_software.crm.utils.ApiResponse;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @PostMapping
    public ResponseEntity<ApiResponse<EnquiryDTO>> createEnquiry(@RequestBody EnquiryDTO enquiryDTO) {
        ApiResponse<EnquiryDTO> response = enquiryService.createEnquiry(enquiryDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{enquiryId}")
    public ResponseEntity<ApiResponse<EnquiryDTO>> updateEnquiry(@PathVariable("enquiryId") Long enquiryId, @RequestBody EnquiryDTO enquiryDTO) {
        ApiResponse<EnquiryDTO> response = enquiryService.updateEnquiry(enquiryId, enquiryDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{enquiryId}/approve")
    public ResponseEntity<ApiResponse<Void>> approveEnquiryAndCreatePurchaseOrder(
            @PathVariable("enquiryId") Long enquiryId,
            @RequestBody List<Long> productEnquiryHistoryIds) {
        ApiResponse<Void> response = enquiryService.approveEnquiryAndCreatePurchaseOrder(enquiryId, productEnquiryHistoryIds);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{enquiryId}/reject")
    public ResponseEntity<ApiResponse<Void>> rejectEnquiry(@PathVariable("enquiryId") Long enquiryId) {
        ApiResponse<Void> response = enquiryService.rejectEnquiry(enquiryId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/{enquiryId}/purchase-order")
    public ResponseEntity<ApiResponse<Void>> createPurchaseOrder(@PathVariable("enquiryId") Long enquiryId) {
        ApiResponse<Void> response = enquiryService.createPurchaseOrder(enquiryId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EnquiryDTO>>> getAllEnquiries() {
        ApiResponse<List<EnquiryDTO>> response = enquiryService.getAllEnquiries();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<EnquiryDTO>>> getEnquiriesByStatus(@PathVariable("status") String status) {
        ApiResponse<List<EnquiryDTO>> response = enquiryService.getEnquiriesByStatus(status);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{enquiryId}/status")
    public ResponseEntity<ApiResponse<EnquiryDTO>> getEnquiryStatusById(@PathVariable("enquiryId") Long enquiryId) {
        ApiResponse<EnquiryDTO> response = enquiryService.getEnquiryStatusById(enquiryId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{enquiryId}")
    public ResponseEntity<ApiResponse<Void>> deleteEnquiry(@PathVariable("enquiryId") Long enquiryId) {
        ApiResponse<Void> response = enquiryService.deleteEnquiry(enquiryId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
