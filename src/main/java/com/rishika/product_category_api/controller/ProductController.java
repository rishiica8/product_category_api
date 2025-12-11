package com.rishika.product_category_api.controller;

import com.rishika.product_category_api.dtos.CategoryResponseDTO;
import com.rishika.product_category_api.dtos.ProductRequestDTO;
import com.rishika.product_category_api.dtos.ProductResponseDTO;
import com.rishika.product_category_api.dtos.ProductUpdateDTO;
import com.rishika.product_category_api.exception.ProductNotFoundException;
import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.models.Product;
import com.rishika.product_category_api.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductController {
    private ProductServiceImpl service;

    public ProductController(ProductServiceImpl inputService) {
        this.service = inputService;
    }

    @GetMapping("/products/{id}")
    public  ResponseEntity<ProductResponseDTO>getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product product=service.getProductById(id);
        return ResponseEntity.ok(convertProductToResponseDTO(product));
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = service.getAllProducts();
        List<ProductResponseDTO> responseList = products.stream()
                .map(this::convertProductToResponseDTO)
                .toList();

        return ResponseEntity.ok(responseList);
    }
    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request) {
        Product savedProduct=service.createProduct(request);
        return ResponseEntity.ok(convertProductToResponseDTO(savedProduct));
    }

    private ProductResponseDTO convertProductToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
        categoryDTO.setId(product.getCategory().getId());
        categoryDTO.setTitle(product.getCategory().getTitle());

        dto.setCategory(categoryDTO);
        return dto;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id)throws ProductNotFoundException{
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProductUpdateDTO dto) {

        Product updatedProduct = service.updateProduct(id, dto);
        ProductResponseDTO response = service.convertProductToResponseDTO(updatedProduct);
        return ResponseEntity.ok(response);
    }
}
