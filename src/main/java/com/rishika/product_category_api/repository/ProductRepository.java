package com.rishika.product_category_api.repository;

import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
    Product save(Product p);
    Optional<Product> findByCategory(Category c);
    List<Product> findAll();
    Optional<List<Product>> findAllByCategory(Category c);
    void deleteById(Integer id);
}
