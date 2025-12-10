package com.rishika.product_category_api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private CategoryResponseDTO category;
}
