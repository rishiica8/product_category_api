package com.rishika.product_category_api.service;

import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.models.Product;
import com.rishika.product_category_api.repository.CategoryRepository;
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
   public Category getCategoryById(Integer id){return new Category();}
    public Category createCategory(Category category){return new  Category();}
   public List<Category> getAllCategories(){return  categoryRepo.findAll();}
}
