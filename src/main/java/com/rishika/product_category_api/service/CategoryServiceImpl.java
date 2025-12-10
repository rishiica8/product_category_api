package com.rishika.product_category_api.service;


import com.rishika.product_category_api.dtos.CategoryRequestDTO;
import com.rishika.product_category_api.dtos.CategoryResponseDTO;
import com.rishika.product_category_api.exception.CategoryNotFoundException;
import com.rishika.product_category_api.exception.DuplicateNameException;
import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.repository.CategoryRepository;
import com.rishika.product_category_api.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepo;


    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo= categoryRepo;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category createCategory(CategoryRequestDTO dto) {

        Optional<Category> existing = categoryRepo.findByTitle(dto.getTitle());
        if (existing.isPresent()) {
            throw new DuplicateNameException("Category name must be unique");
        }

        Category category = new Category();
        category.setTitle(dto.getTitle());
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());

        return categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new CategoryNotFoundException("Category not found");
        }
        categoryRepo.deleteById(id);
    }

    // Convert Entity → DTO
    public CategoryResponseDTO convertToDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        return dto;
    }

}
