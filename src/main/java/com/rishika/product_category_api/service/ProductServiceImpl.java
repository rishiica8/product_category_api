package com.rishika.product_category_api.service;

import com.rishika.product_category_api.dtos.CategoryResponseDTO;
import com.rishika.product_category_api.dtos.ProductRequestDTO;
import com.rishika.product_category_api.dtos.ProductResponseDTO;
import com.rishika.product_category_api.exception.ProductNotFoundException;
import com.rishika.product_category_api.models.Category;
import com.rishika.product_category_api.models.Product;
import com.rishika.product_category_api.repository.CategoryRepository;
import com.rishika.product_category_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements  ProductService{
    private ProductRepository productRepo;
    private CategoryRepository categoryRepo;

    public ProductServiceImpl(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo= categoryRepo;
    }
    @Override
    public Product getProductById(Integer id){
        Optional<Product> response= productRepo.findById(id);
        if(!response.isPresent()){
            throw new IllegalArgumentException("Product not found");
        }
        return response.get();
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    public Product createProduct(ProductRequestDTO dto){
        Product product =new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        Optional<Category> existing = categoryRepo.findByTitle(dto.getCategory().getTitle());

        Category category;
        if (existing.isPresent()) {
            category = existing.get();
        } else {
            category = new Category();
            category.setTitle(dto.getCategory().getTitle());
            categoryRepo.save(category);
        }

        product.setCategory(category);
        Product response = productRepo.save(product);
        return response;
    }
    // we can do all the validations here
    private void validateInputRequest(Product p) {
        if (p.getTitle() == null || p.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null");
        }
    }
    @Override
    public void deleteProduct(Integer id) {
        if(!productRepo.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepo.deleteById(id);
    }
    public ProductResponseDTO convertProductToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
        categoryDTO.setId(product.getCategory().getId());
        categoryDTO.setTitle(product.getCategory().getTitle());
        dto.setCategory(categoryDTO);

        return dto;
    }
    private CategoryResponseDTO convertCategorytoResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        return dto;
    }
}
