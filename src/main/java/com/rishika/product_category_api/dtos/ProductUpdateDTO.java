package com.rishika.product_category_api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProductUpdateDTO {
    private String title;
    private BigDecimal price;
    private int quantity;
    private Date UpdatedAt;
    private Long categoryId;
}
