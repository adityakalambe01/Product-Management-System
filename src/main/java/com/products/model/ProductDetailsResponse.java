package com.products.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsResponse {
    private Long productId;
    private String productName;
    private Double productCost;
    private CategoryResponse category;

    public ProductDetailsResponse(Long productId, String productName, Double productCost, CategoryResponse category) {
        this.productId = productId;
        this.productName = productName;
        this.productCost = productCost;
        this.category = category;
    }
}

