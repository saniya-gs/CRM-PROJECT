package com.ourdept.crm_software.crm.repository.crm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.crm.ProcessHistory;
import com.ourdept.crm_software.crm.domain.enums.crm.ProcessStatus;

public interface ProcessHistoryRepository extends JpaRepository<ProcessHistory, Long> {
    // Fetch process histories by status and assigned employee
    List<ProcessHistory> findByStatusAndEmployeeAssigned_EmpId(ProcessStatus status, Long employeeId);

    // Fetch process histories by purchase order ID
    List<ProcessHistory> findByProductOrderHistory_PurchaseOrder_Id(Long purchaseOrderId);

    // Fetch process histories by process stage ID
    List<ProcessHistory> findByProcessStage_Id(Long processStageId);

    // Fetch process histories by process stage ID and assigned employee ID
    List<ProcessHistory> findByProcessStage_IdAndEmployeeAssigned_EmpId(Long processStageId, Long employeeId);
}
