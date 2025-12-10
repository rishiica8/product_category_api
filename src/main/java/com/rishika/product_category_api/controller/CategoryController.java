package com.rishika.product_category_api.controller;

import com.rishika.product_category_api.exception.CategoryNotFoundException;
import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.models.Product;
import com.rishika.product_category_api.service.CategoryServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private CategoryServiceImpl service;

    public CategoryController(CategoryServiceImpl inputService) {
        this.service = inputService;
    }



}
