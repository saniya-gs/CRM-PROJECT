package com.ourdept.crm_software.crm.domain.entities.crm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ourdept.crm_software.crm.domain.entities.core.BaseEntity;
import com.ourdept.crm_software.crm.domain.enums.crm.OrderStatus;
import com.ourdept.crm_software.crm.domain.enums.crm.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchaseOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseOrderId")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enquiryId", nullable = false)
    private Enquiry enquiry;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrderHistory> productOrderHistories = new ArrayList<>();

    @Column(name = "billAmount", nullable = false)
    private Double billAmount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "purchase_order_process_history",
        joinColumns = @JoinColumn(name = "purchaseOrderId"),
        inverseJoinColumns = @JoinColumn(name = "processHistoryId")
    )
    private List<ProcessHistory> processHistories;

    @Enumerated(EnumType.STRING)
    @Column(name = "orderStatus", nullable = false)
    private OrderStatus orderStatus; // Enum: PROCESSING, COMPLETED

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentStatus", nullable = false)
    private PaymentStatus paymentStatus; // Enum: PAID, PENDING

    @Column(name = "paidDate")
    private LocalDate paidDate;
}
