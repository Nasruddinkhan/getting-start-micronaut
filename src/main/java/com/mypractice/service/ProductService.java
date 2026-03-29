package com.mypractice.service;

import com.mypractice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProducts();

    Optional<Product> getProductById(Integer id);

    Product createProduct(Product product);

    Optional<Product> updateProduct(Integer id, Product product);

    boolean deleteProduct(Integer id);
}
