package com.ourdept.crm_software.crm.modules.enquiry.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ourdept.crm_software.ExceptionHandler.ConflictException;
import com.ourdept.crm_software.ExceptionHandler.NotFoundException;
import com.ourdept.crm_software.crm.domain.entities.crm.Enquiry;
import com.ourdept.crm_software.crm.domain.entities.crm.ProcessHistory;
import com.ourdept.crm_software.crm.domain.entities.crm.ProcessStage;
import com.ourdept.crm_software.crm.domain.entities.crm.Product;
import com.ourdept.crm_software.crm.domain.entities.crm.ProductEnquiryHistory;
import com.ourdept.crm_software.crm.domain.entities.crm.ProductOrderHistory;
import com.ourdept.crm_software.crm.domain.entities.crm.PurchaseOrder;
import com.ourdept.crm_software.crm.domain.enums.crm.EnquiryStatus;
import com.ourdept.crm_software.crm.domain.enums.crm.OrderStatus;
import com.ourdept.crm_software.crm.domain.enums.crm.PaymentStatus;
import com.ourdept.crm_software.crm.domain.enums.crm.ProcessStatus;
import com.ourdept.crm_software.crm.modules.enquiry.dto.EnquiryDTO;
import com.ourdept.crm_software.crm.modules.enquiry.service.interfaces.EnquiryService;
import com.ourdept.crm_software.crm.repository.crm.EnquiryRepository;
import com.ourdept.crm_software.crm.repository.crm.ProcessHistoryRepository;
import com.ourdept.crm_software.crm.repository.crm.ProcessStageRepository;
import com.ourdept.crm_software.crm.repository.crm.ProductEnquiryHistoryRepository;
import com.ourdept.crm_software.crm.repository.crm.ProductRepository;
import com.ourdept.crm_software.crm.repository.crm.PurchaseOrderRepository;
import com.ourdept.crm_software.crm.utils.ApiResponse;
import com.ourdept.crm_software.crm.utils.HttpStatusCodes;
import com.ourdept.crm_software.crm.utils.ResponseHandler;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private ProductEnquiryHistoryRepository enquiryHistoryRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProcessStageRepository processStageRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ProcessHistoryRepository processHistoryRepository;

    @Autowired
    private ResponseHandler<EnquiryDTO> responseHandler;

    @Autowired
    private ResponseHandler<List<EnquiryDTO>> listResponseHandler;

    @Autowired
    private ResponseHandler<Void> voidResponseHandler;

    @Override
    public ApiResponse<EnquiryDTO> createEnquiry(EnquiryDTO enquiryDTO) {
        Enquiry enquiry = mapToEntity(enquiryDTO);
        enquiry.setStatus(EnquiryStatus.PENDING);
        Enquiry savedEnquiry = enquiryRepository.save(enquiry);

        return responseHandler.success(mapToDTO(savedEnquiry), "Enquiry created successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<EnquiryDTO> updateEnquiry(Long enquiryId, EnquiryDTO enquiryDTO) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new NotFoundException("Enquiry not found"));

        // Update fields and save
        enquiry.setContactPerson(enquiryDTO.getContactPerson());
        enquiry.setEmail(enquiryDTO.getEmail());
        enquiry.setMobileNumber(enquiryDTO.getMobileNumber());
        enquiry.setCity(enquiryDTO.getCity());
        enquiry.setState(enquiryDTO.getState());
        enquiry.setNotes(enquiryDTO.getNotes());
        enquiry.setDiscountPercentage(enquiryDTO.getDiscountPercentage());

        Enquiry updatedEnquiry = enquiryRepository.save(enquiry);
        return responseHandler.success(mapToDTO(updatedEnquiry), "Enquiry updated successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> approveEnquiryAndCreatePurchaseOrder(Long enquiryId, List<Long> productEnquiryHistoryIds) {
        // Fetch the enquiry
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new NotFoundException("Enquiry not found"));

        // Fetch and approve ProductEnquiryHistory records
        List<ProductEnquiryHistory> approvedHistories = enquiryHistoryRepository.findAllById(productEnquiryHistoryIds);

        if (approvedHistories.isEmpty()) {
            return voidResponseHandler.error("No ProductEnquiryHistory records provided for approval", HttpStatus.BAD_REQUEST.value());
        }

        double totalBillAmount = 0.0;

        // Create Purchase Order
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setEnquiry(enquiry);
        purchaseOrder.setOrderStatus(OrderStatus.PROCESSING);
        purchaseOrder.setPaymentStatus(PaymentStatus.PENDING);

        // Process ProductEnquiryHistory and create ProductOrderHistory
        for (ProductEnquiryHistory history : approvedHistories) {
            if (history.getStatus() == EnquiryStatus.APPROVED) {
                return voidResponseHandler.error("One or more ProductEnquiryHistory records are already approved", HttpStatus.CONFLICT.value());
            }

            // Approve the history record
            history.setStatus(EnquiryStatus.APPROVED);
            enquiryHistoryRepository.save(history);

            // Create ProductOrderHistory
            ProductOrderHistory orderHistory = new ProductOrderHistory();
            orderHistory.setProducts(history.getProduct());
            orderHistory.setQuantity(history.getQuantity());
            orderHistory.setPurchaseOrder(purchaseOrder);

            // Deduct quantity from product stock
            Product product = history.getProduct();
            if (product.getQuantityAvailable() < history.getQuantity()) {
                return voidResponseHandler.error(
                        "Insufficient stock for product: " + product.getName(),
                        HttpStatus.CONFLICT.value());
            }

            product.setQuantityAvailable(product.getQuantityAvailable() - history.getQuantity());
            productRepository.save(product);

            // Add order history to purchase order
            purchaseOrder.getProductOrderHistories().add(orderHistory);

            // Calculate bill amount
            totalBillAmount += product.getPrice() * history.getQuantity();

            // Create ProcessHistory for the first process stage
            ProcessStage firstStage = getProcessStage();
            if (firstStage != null) {
                ProcessHistory processHistory = new ProcessHistory();
                processHistory.setProcessStage(firstStage);
                processHistory.setProduct(product);
                processHistory.setStatus(ProcessStatus.ASSIGNED);
                processHistory.setProductOrderHistory(orderHistory);
                processHistory.setReturned(false);

                // Save process history
                processHistoryRepository.save(processHistory);

                // Add the process history to the ProductOrderHistory
                orderHistory.getProcessHistories().add(processHistory);
            }
        }

        // Save purchase order
        purchaseOrder.setBillAmount(totalBillAmount);
        purchaseOrderRepository.save(purchaseOrder);

        // Update Enquiry status
        enquiry.setStatus(EnquiryStatus.APPROVED);
        enquiryRepository.save(enquiry);

        return voidResponseHandler.success(null, "Enquiry approved, purchase order and process histories created successfully", HttpStatusCodes.OK);
    }


    private ProcessStage getProcessStage() {
//    	get the first stage in the squence
    	ProcessStage stage=	processStageRepository.findBySequenceNumber(1);
		return stage;
	}

	@Override
    public ApiResponse<Void> rejectEnquiry(Long enquiryId) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new NotFoundException("Enquiry not found"));

        enquiry.setStatus(EnquiryStatus.REJECTED);
        enquiryRepository.save(enquiry);

        return voidResponseHandler.success(null, "Enquiry rejected successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> createPurchaseOrder(Long enquiryId) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new NotFoundException("Enquiry not found"));

        List<ProductEnquiryHistory> approvedHistories = enquiryHistoryRepository
                .findByEnquiryAndStatus(enquiry, EnquiryStatus.APPROVED);

        // Create Purchase Order
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setEnquiry(enquiry);

        double totalAmount = 0.0;
        for (ProductEnquiryHistory history : approvedHistories) {
            Product product = history.getProduct();
            int quantity = history.getQuantity();
            product.setQuantityAvailable(product.getQuantityAvailable() - quantity);

            ProductOrderHistory orderHistory = new ProductOrderHistory();
            orderHistory.setProducts(product);
            orderHistory.setQuantity(quantity);
            orderHistory.setPurchaseOrder(purchaseOrder);
            purchaseOrder.getProductOrderHistories().add(orderHistory);

            totalAmount += product.getPrice() * quantity;

            // Create Process History for first stage
            ProcessHistory processHistory = new ProcessHistory();
            processHistory.setProcessStage(getProcessStage());
            processHistory.setProduct(product);
            processHistory.setStatus(ProcessStatus.ASSIGNED);
            processHistory.setProductOrderHistory(orderHistory);
            processHistoryRepository.save(processHistory);
        }

        purchaseOrder.setBillAmount(totalAmount);
        purchaseOrder.setOrderStatus(OrderStatus.PROCESSING);
        purchaseOrder.setPaymentStatus(PaymentStatus.PENDING);

        purchaseOrderRepository.save(purchaseOrder);

        return voidResponseHandler.success(null, "Purchase order created successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<EnquiryDTO>> getAllEnquiries() {
        List<Enquiry> enquiries = enquiryRepository.findAll();
        return listResponseHandler.success(enquiries.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "All enquiries fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<EnquiryDTO>> getEnquiriesByStatus(String status) {
        EnquiryStatus enquiryStatus = EnquiryStatus.valueOf(status.toUpperCase());
        List<Enquiry> enquiries = enquiryRepository.findByStatus(enquiryStatus);
        return listResponseHandler.success(enquiries.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "Enquiries fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<EnquiryDTO> getEnquiryStatusById(Long enquiryId) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new NotFoundException("Enquiry not found"));

        EnquiryDTO dto=   mapToDTO(enquiry);
        return responseHandler.success(dto, "Enquiry status fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> deleteEnquiry(Long enquiryId) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new NotFoundException("Enquiry not found"));

        if (purchaseOrderRepository.existsByEnquiry(enquiry)) {
          throw new ConflictException("canot delete the enquiry as the purchase order is already created");
        }

        enquiryRepository.delete(enquiry);
        return voidResponseHandler.success(null, "Enquiry deleted successfully", HttpStatusCodes.OK);
    }

    private Enquiry mapToEntity(EnquiryDTO dto) {
        Enquiry enquiry = new Enquiry();
        // Map fields from DTO to entity
        return enquiry;
    }

    private EnquiryDTO mapToDTO(Enquiry enquiry) {
        EnquiryDTO dto = new EnquiryDTO();
        // Map fields from entity to DTO
        return dto;
    }
}
