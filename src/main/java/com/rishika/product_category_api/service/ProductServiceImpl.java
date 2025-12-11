package com.rishika.product_category_api.service;

import com.rishika.product_category_api.dtos.CategoryResponseDTO;
import com.rishika.product_category_api.dtos.ProductRequestDTO;
import com.rishika.product_category_api.dtos.ProductResponseDTO;
import com.rishika.product_category_api.dtos.ProductUpdateDTO;
import com.rishika.product_category_api.exception.DuplicateNameException;
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
    public Product getProductById(Long id){
        Optional<Product> product = productRepo.findById(id);

        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product not found");
        }

        return product.get();
    }
    @Override
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    public Product createProduct(ProductRequestDTO dto){
        if (productRepo.findByTitle(dto.getTitle()).isPresent()) {
            throw new DuplicateNameException("Product name must be unique");
        }
        Category category = categoryRepo.findByTitle(dto.getCategory().getTitle())
                .orElseGet(() -> {
                    // Only create category when product is being created
                    Category newCategory = new Category();
                    newCategory.setTitle(dto.getCategory().getTitle());
                    newCategory.setCreatedAt(new Date());
                    newCategory.setUpdatedAt(new Date());
                    return categoryRepo.save(newCategory);
                });
        Product product =new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        product.setCategory(category);
        Product response = productRepo.save(product);
        return response;
    }
    @Override
    public void deleteProduct(Long id) {
        if(!productRepo.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
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
    @Override
    public Product updateProduct(Long id, ProductUpdateDTO dto) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // check duplicate title
        if (!existingProduct.getTitle().equals(dto.getTitle()) &&
                productRepo.findByTitle(dto.getTitle()).isPresent()) {
            throw new DuplicateNameException("Product title must be unique");
        }

        // fetch category using categoryId
        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with given ID"));

        existingProduct.setTitle(dto.getTitle());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setQuantity(dto.getQuantity());
        existingProduct.setCategory(category);
        existingProduct.setUpdatedAt(new Date());

        return productRepo.save(existingProduct);

    }
}
