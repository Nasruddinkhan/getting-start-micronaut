package com.mypractice.service.impl;

import com.mypractice.model.ProdcutType;
import com.mypractice.model.Product;
import com.mypractice.service.ProductCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProductServiceImplTest {

    @Test
    void shouldReturnProductsFromCache() {
        ProductCache productCache = new ProductCache();
        ProductServiceImpl productService = new ProductServiceImpl(productCache);

        List<Product> products = productService.getProducts();

        Assertions.assertEquals(productCache.getProducts(), products);
    }

    @Test
    void shouldReturnProductById() {
        ProductServiceImpl productService = new ProductServiceImpl(new ProductCache());

        Product product = productService.getProductById(1).orElseThrow();

        Assertions.assertEquals(new Product(1, "Dummy Laptop", ProdcutType.ELECTRONICS), product);
    }

    @Test
    void shouldCreateProduct() {
        ProductServiceImpl productService = new ProductServiceImpl(new ProductCache());

        Product createdProduct = productService.createProduct(new Product(null, "Desk Lamp", ProdcutType.HOME_APPLIANCE));

        Assertions.assertEquals(5, createdProduct.productid());
        Assertions.assertEquals("Desk Lamp", createdProduct.name());
    }

    @Test
    void shouldUpdateExistingProduct() {
        ProductServiceImpl productService = new ProductServiceImpl(new ProductCache());

        Product updatedProduct = productService.updateProduct(4, new Product(null, "Smart Air Fryer", ProdcutType.HOME_APPLIANCE))
                .orElseThrow();

        Assertions.assertEquals(new Product(4, "Smart Air Fryer", ProdcutType.HOME_APPLIANCE), updatedProduct);
    }

    @Test
    void shouldDeleteExistingProduct() {
        ProductServiceImpl productService = new ProductServiceImpl(new ProductCache());

        boolean deleted = productService.deleteProduct(2);

        Assertions.assertTrue(deleted);
        Assertions.assertTrue(productService.getProductById(2).isEmpty());
    }
}
