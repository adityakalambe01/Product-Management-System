package com.products.service;

import com.products.model.*;
import com.products.repositories.CategoryRepository;
import com.products.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryService;

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
    public List<Product> getAllProducts(Integer pageNumber, Integer pageSize) {

        return productRepository.findAll(PageRequest.of(pageNumber,pageSize)).getContent();
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
