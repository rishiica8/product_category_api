package com.rishika.product_category_api.service;

import com.rishika.product_category_api.models.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Integer id);
    Category createCategory(Category category);
    List<Category> getAllCategories();
}
