package com.ourdept.crm_software.crm.domain.entities.crm;

import java.util.ArrayList;
import java.util.List;

import com.ourdept.crm_software.crm.domain.entities.core.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class ProductOrderHistory extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderHistoryId")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductId")
	private Product products;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@OneToMany(mappedBy = "productOrderHistory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProcessHistory> processHistories = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchaseOrderId", nullable = false)
	private PurchaseOrder purchaseOrder;
}
