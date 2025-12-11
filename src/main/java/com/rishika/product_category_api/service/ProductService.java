package com.rishika.product_category_api.service;

import com.rishika.product_category_api.dtos.ProductRequestDTO;
import com.rishika.product_category_api.dtos.ProductUpdateDTO;
import com.rishika.product_category_api.models.Product;
import jakarta.validation.constraints.Future;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequestDTO dto);
    Product getProductById(Long id);
    List<Product> getAllProducts(Long categoryId, Double minPrice, Double maxPrice);
     void deleteProduct(Long id);
     Product updateProduct(Long id, ProductUpdateDTO dto);
}
