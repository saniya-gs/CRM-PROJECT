package com.ourdept.crm_software.crm.repository.crm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.crm.Enquiry;
import com.ourdept.crm_software.crm.domain.entities.crm.ProductEnquiryHistory;
import com.ourdept.crm_software.crm.domain.enums.crm.EnquiryStatus;

public interface ProductEnquiryHistoryRepository extends JpaRepository<ProductEnquiryHistory, Long>  {

	List<ProductEnquiryHistory> findByEnquiryAndStatus(Enquiry enquiry, EnquiryStatus approved);

}
