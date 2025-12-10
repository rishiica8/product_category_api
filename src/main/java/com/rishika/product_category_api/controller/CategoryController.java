package com.rishika.product_category_api.controller;

import com.rishika.product_category_api.dtos.CategoryRequestDTO;
import com.rishika.product_category_api.dtos.CategoryResponseDTO;
import com.rishika.product_category_api.dtos.ProductRequestDTO;
import com.rishika.product_category_api.dtos.ProductResponseDTO;
import com.rishika.product_category_api.exception.CategoryNotFoundException;
import com.rishika.product_category_api.exception.ProductNotFoundException;
import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.models.Product;


import com.rishika.product_category_api.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private CategoryServiceImpl service;

    public CategoryController(CategoryServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        Category category = service.getCategoryById(id);
        return ResponseEntity.ok(service.convertToDTO(category));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> list = service.getAllCategories().stream()
                .map(service::convertToDTO)
                .toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO dto) {
        Category saved = service.createCategory(dto);
        return ResponseEntity.ok(service.convertToDTO(saved));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
