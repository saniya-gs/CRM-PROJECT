package com.ourdept.crm_software.crm.modules.product.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourdept.crm_software.ExceptionHandler.NotFoundException;
import com.ourdept.crm_software.crm.domain.entities.crm.Product;
import com.ourdept.crm_software.crm.modules.product.dto.ProductDTO;
import com.ourdept.crm_software.crm.modules.product.service.interfaces.ProductService;
import com.ourdept.crm_software.crm.repository.crm.ProductRepository;
import com.ourdept.crm_software.crm.utils.ApiResponse;
import com.ourdept.crm_software.crm.utils.HttpStatusCodes;
import com.ourdept.crm_software.crm.utils.ResponseHandler;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ResponseHandler<ProductDTO> responseHandler;

    @Autowired
    private ResponseHandler<List<ProductDTO>> listResponseHandler;

    @Autowired
    private ResponseHandler<Void> voidResponseHandler;

    @Override
    public ApiResponse<ProductDTO> createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setQuantityAvailable(productDTO.getQuantityAvailable());
        product.setDescription(productDTO.getDescription());

        Product savedProduct = productRepository.save(product);

        return responseHandler.success(mapToDTO(savedProduct), "Product created successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<ProductDTO> updateProduct(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        product.setName(productDTO.getName());
        product.setQuantityAvailable(productDTO.getQuantityAvailable());
        product.setDescription(productDTO.getDescription());

        Product updatedProduct = productRepository.save(product);
        return responseHandler.success(mapToDTO(updatedProduct), "Product updated successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> updateProductQuantity(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        product.setQuantityAvailable(quantity);
        productRepository.save(product);

        return voidResponseHandler.success(null, "Product quantity updated successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        productRepository.delete(product);
        return voidResponseHandler.success(null, "Product deleted successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<Void> updateProductStatus(Long productId, String status) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        product.setDescription(status);
        productRepository.save(product);

        return voidResponseHandler.success(null, "Product status updated successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ProductDTO>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return listResponseHandler.success(
                products.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "All products fetched successfully", HttpStatusCodes.OK);
    }

    @Override
    public ApiResponse<List<ProductDTO>> getAllProductsWithQuantity() {
        List<Product> products = productRepository.findByQuantityAvailableGreaterThan(0);
        return listResponseHandler.success(
                products.stream().map(this::mapToDTO).collect(Collectors.toList()),
                "Products with available quantity fetched successfully", HttpStatusCodes.OK);
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setQuantityAvailable(product.getQuantityAvailable());
        dto.setDescription(product.getDescription());
        return dto;
    }
}
