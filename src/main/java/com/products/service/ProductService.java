package com.products.service;

import com.products.model.*;
import com.products.repositories.CategoryRepository;
import com.products.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /*
    *
    * Add Product
    *
    * */
    public boolean addProduct(Product product) {

        productRepository.save(product);
        return true;
    }

    /*
    *
    * Get Product By Id
    *
    * */
    public Product getProductById(Long productId) {
        Optional<Product> dbProduct = productRepository.findById(productId);
        return dbProduct.orElse(null);
    }

    /*
    *
    * Delete Product by Id
    *
    * */
    public boolean deleteProductById(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    /*
    *
    * Get all Products
    *
    * */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /*
    *
    * Update Product
    *
    * */
    public boolean updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            BeanUtils.copyProperties(updatedProduct, product, "id");
            productRepository.save(product);
            return true;
        }
        return false;
    }




}
