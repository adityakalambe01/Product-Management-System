package com.products.controller;

import com.products.model.Categories;
import com.products.model.CategoryResponse;
import com.products.model.Product;
import com.products.model.ProductDetailsResponse;
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

    /*
    *
    * Add Product
    *
    * */
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return productService.addProduct(product) ?
                ResponseEntity.ok("Product added successfully") : ResponseEntity.internalServerError().body("Failed to add product");
    }

    /*
    *
    * Get product By Id
    *
    * */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long productId){
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid product id "+productId);
        }

        Categories category = product.getCategory();
        if (category == null) {
            return ResponseEntity.internalServerError().body("Category Doesn't Exists of product id "+productId);
        }

        ProductDetailsResponse response = new ProductDetailsResponse(product.getId(), product.getName(), product.getCost(), new CategoryResponse(category.getCategoryId(), category.getCategoryName(), category.getCategoryDescription()));

        return ResponseEntity.ok(response);
    }


    /*
    *
    * Get All Products
    *
    * */
    @GetMapping
    public ResponseEntity<List<ProductDetailsResponse>> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer pageSize
    ) {
        List<Product> products = productService.getAllProducts(pageNumber, pageSize);
        List<ProductDetailsResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            Categories category = product.getCategory();
            if (category != null) {
                CategoryResponse categoryResponse = new CategoryResponse(category.getCategoryId(), category.getCategoryName(), category.getCategoryDescription());
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
        return productService.deleteProductById(productId) ? ResponseEntity.ok("Product deleted successfully") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid product id "+productId);
    }

    /*
    *
    * Update Product by Id
    *
    * */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long productId, @RequestBody Product updatedProduct) {
        return productService.updateProduct(productId, updatedProduct) ? ResponseEntity.ok("Product updated successfully") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid product id "+productId+" or category id "+updatedProduct.getCategory().getCategoryId());
    }

    @RequestMapping("/addDefault")
    public ResponseEntity<String> addDefaultProducts(){

        return ResponseEntity.ok().body(productService.addDefaultProducts());
    }
}
