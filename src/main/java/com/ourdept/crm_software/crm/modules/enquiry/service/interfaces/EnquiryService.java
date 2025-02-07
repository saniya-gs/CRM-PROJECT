package com.ourdept.crm_software.crm.modules.enquiry.service.interfaces;


import java.util.List;

import com.ourdept.crm_software.crm.modules.enquiry.dto.EnquiryDTO;
import com.ourdept.crm_software.crm.utils.ApiResponse;

public interface EnquiryService {
    ApiResponse<EnquiryDTO> createEnquiry(EnquiryDTO enquiryDTO);
    ApiResponse<EnquiryDTO> updateEnquiry(Long enquiryId, EnquiryDTO enquiryDTO);
    public ApiResponse<Void> approveEnquiryAndCreatePurchaseOrder(Long enquiryId, List<Long> productEnquiryHistoryIds) ;
    ApiResponse<Void> rejectEnquiry(Long enquiryId);
    ApiResponse<Void> createPurchaseOrder(Long enquiryId);
    ApiResponse<List<EnquiryDTO>> getAllEnquiries();
    ApiResponse<List<EnquiryDTO>> getEnquiriesByStatus(String status);
    ApiResponse<EnquiryDTO> getEnquiryStatusById(Long enquiryId);
    ApiResponse<Void> deleteEnquiry(Long enquiryId);
}
