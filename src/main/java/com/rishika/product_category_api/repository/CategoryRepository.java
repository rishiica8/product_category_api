package com.rishika.product_category_api.repository;

import com.rishika.product_category_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(Integer id);
    Optional<Category> findByTitle(String title);
}
