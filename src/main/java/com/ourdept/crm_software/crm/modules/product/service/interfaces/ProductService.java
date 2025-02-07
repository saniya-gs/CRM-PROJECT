package com.ourdept.crm_software.crm.modules.product.service.interfaces;

import java.util.List;

import com.ourdept.crm_software.crm.modules.product.dto.ProductDTO;
import com.ourdept.crm_software.crm.utils.ApiResponse;

public interface ProductService {
    ApiResponse<ProductDTO> createProduct(ProductDTO productDTO);
    ApiResponse<ProductDTO> updateProduct(Long productId, ProductDTO productDTO);
    ApiResponse<Void> updateProductQuantity(Long productId, Integer quantity);
    ApiResponse<Void> deleteProduct(Long productId);
    ApiResponse<Void> updateProductStatus(Long productId, String status);
    ApiResponse<List<ProductDTO>> getAllProducts();
    ApiResponse<List<ProductDTO>> getAllProductsWithQuantity();
}

