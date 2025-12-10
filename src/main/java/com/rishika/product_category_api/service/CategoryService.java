package com.rishika.product_category_api.service;

import com.rishika.product_category_api.dtos.CategoryRequestDTO;
import com.rishika.product_category_api.models.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);
    Category createCategory(CategoryRequestDTO category);
    List<Category> getAllCategories();
    void deleteCategory(Long id);
}
