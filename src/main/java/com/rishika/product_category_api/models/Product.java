package com.rishika.product_category_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    private String title;
    private BigDecimal price;
    private Integer quantity;
    @ManyToOne( cascade = CascadeType.ALL)
    @JsonIgnore
    private Category category;
}
