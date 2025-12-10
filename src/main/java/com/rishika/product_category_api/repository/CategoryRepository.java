package com.rishika.product_category_api.repository;

import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);
    Optional<Category> findByTitle(String title);
    List<Category> findAll();
    void deleteById(Long id);
    Category save(Category category);
}
