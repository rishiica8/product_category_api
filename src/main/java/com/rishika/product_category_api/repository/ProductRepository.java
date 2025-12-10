package com.rishika.product_category_api.repository;

import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> findByCategory(Category category);
    List<Product> findAll();
    Optional<List<Product>> findAllByCategory(Category category);
    void deleteById(Long id);
    Optional<Product> findByTitle(String title);
}
