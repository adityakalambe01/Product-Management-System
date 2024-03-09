package com.products.controller;

import com.products.model.Categories;
import com.products.model.CategoryResponse;
import com.products.model.Product;
import com.products.model.ProductDetailsResponse;
import com.products.service.CategoryService;
import com.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /*
    *
    * Add Product
    *
    * */
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return productService.addProduct(product) ? ResponseEntity.ok("Product added successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product");
    }

    /*
    *
    * Get product By Id
    *
    * */
    @GetMapping("{id}")
    public ResponseEntity<ProductDetailsResponse> getProductById(@PathVariable("id") Long productId){
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        Categories category = product.getCategory();
        if (category == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        ProductDetailsResponse response = new ProductDetailsResponse(product.getId(), product.getName(), product.getCost(), new CategoryResponse(category.getCategoryId(), category.getCategoryName()));

        return ResponseEntity.ok(response);
    }


    /*
    *
    * Get All Products
    *
    * */
    @GetMapping
    public ResponseEntity<List<ProductDetailsResponse>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDetailsResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            Categories category = product.getCategory();
            if (category != null) {
                CategoryResponse categoryResponse = new CategoryResponse(category.getCategoryId(), category.getCategoryName());
                ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse(product.getId(), product.getName(), product.getCost(), categoryResponse);
                responseList.add(productDetailsResponse);
            }
        }

        return ResponseEntity.ok(responseList);
    }


    /*
    *
    * Delete Product by Id
    *
    * */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Long productId) {
        return productService.deleteProductById(productId) ? ResponseEntity.ok("Product deleted successfully") : ResponseEntity.notFound().build();
    }

    /*
    *
    * Update Product by Id
    *
    * */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long productId, @RequestBody Product updatedProduct) {
        return productService.updateProduct(productId, updatedProduct) ? ResponseEntity.ok("Product updated successfully") : ResponseEntity.notFound().build();
    }
}
