package com.rishika.product_category_api.service;

import com.rishika.product_category_api.dtos.ProductRequestDTO;
import com.rishika.product_category_api.dtos.ProductUpdateDTO;
import com.rishika.product_category_api.models.Product;
import jakarta.validation.constraints.Future;
import org.springframework.data.domain.Page;
//import org.hibernate.query.Page;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequestDTO dto);
    Product getProductById(Long id);
    List<Product> getAllProducts(Long categoryId, Double minPrice, Double maxPrice);
     void deleteProduct(Long id);
     Product updateProduct(Long id, ProductUpdateDTO dto);
     Page<Product> getPaginatedProducts(int pageNo, int pageSize);
}
