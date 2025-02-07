package com.ourdept.crm_software.crm.modules.product.controller;

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

import com.ourdept.crm_software.crm.modules.product.dto.ProductDTO;
import com.ourdept.crm_software.crm.modules.product.service.interfaces.ProductService;
import com.ourdept.crm_software.crm.utils.ApiResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {
        ApiResponse<ProductDTO> response = productService.createProduct(productDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody ProductDTO productDTO) {
        ApiResponse<ProductDTO> response = productService.updateProduct(productId, productDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{productId}/quantity")
    public ResponseEntity<ApiResponse<Void>> updateProductQuantity(
            @PathVariable("productId") Long productId,
            @RequestParam("quantity") Integer quantity) {
        ApiResponse<Void> response = productService.updateProductQuantity(productId, quantity);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable("productId") Long productId) {
        ApiResponse<Void> response = productService.deleteProduct(productId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{productId}/status")
    public ResponseEntity<ApiResponse<Void>> updateProductStatus(
            @PathVariable("productId") Long productId,
            @RequestParam("status") String status) {
        ApiResponse<Void> response = productService.updateProductStatus(productId, status);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        ApiResponse<List<ProductDTO>> response = productService.getAllProducts();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProductsWithQuantity() {
        ApiResponse<List<ProductDTO>> response = productService.getAllProductsWithQuantity();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
