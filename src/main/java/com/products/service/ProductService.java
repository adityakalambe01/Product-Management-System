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

import java.util.*;

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
        try{
            Product newProduct = new Product();
            newProduct.setName(
                    product.getName().trim().replaceAll("\\s+"," ")
            );
            newProduct.setCost(product.getCost());
            newProduct.setCategory(
                    categoryRepository.findById(product.getCategory().getCategoryId()).get()
            );
            productRepository.save(newProduct);
        }catch (Exception e){
            return false; // if any error occur
        }
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
            updatedProduct.setName(
                    updatedProduct.getName().trim().replaceAll("\\s+"," ")
            );
            BeanUtils.copyProperties(updatedProduct, product, "id");
            productRepository.save(product);
            return true;
        }
        return false;
    }

    /*
    *
    * Add Default Products
    *
    * */
    public String addDefaultProducts(){
        List<Categories> allCategories = new LinkedList<>(categoryRepository.findAll());
        System.out.println("\n\n\n\n");
        long index = productRepository.count()+1;
        for (Categories categories : allCategories) {
            for (int j = 0; j < 2; j++) {
                Product p = new Product();
                p.setCategory(categories);
                p.setName("Product " + (index++));
                p.setCost(
                        Math.round((Math.random() * (50000.00 - 30000.00) + 30000.00) * 100.0) / 100.0
                );
                productRepository.save(p);
            }
        }
        return "Default products added successfully!";
    }
}
