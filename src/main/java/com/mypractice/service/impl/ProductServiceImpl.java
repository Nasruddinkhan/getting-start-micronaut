package com.mypractice.service.impl;

import com.mypractice.model.Product;
import com.mypractice.service.ProductCache;
import com.mypractice.service.ProductService;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class ProductServiceImpl implements ProductService {

    private final ProductCache productCache;

    public ProductServiceImpl(ProductCache productCache) {
        this.productCache = productCache;
    }

    @Override
    public List<Product> getProducts() {
        return productCache.getProducts();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productCache.getProductById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productCache.createProduct(product);
    }

    @Override
    public Optional<Product> updateProduct(Integer id, Product product) {
        return productCache.updateProduct(id, product);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return productCache.deleteProduct(id);
    }
}
