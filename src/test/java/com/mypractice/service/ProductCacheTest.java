package com.mypractice.service;

import com.mypractice.model.ProdcutType;
import com.mypractice.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProductCacheTest {

    @Test
    void shouldReturnDefaultProductsFromCache() {
        ProductCache productCache = new ProductCache();

        List<Product> products = productCache.getProducts();

        Assertions.assertEquals(4, products.size());
        Assertions.assertEquals(new Product(1, "Dummy Laptop", ProdcutType.ELECTRONICS), products.getFirst());
        Assertions.assertEquals(new Product(4, "Air Fryer", ProdcutType.HOME_APPLIANCE), products.getLast());
    }

    @Test
    void shouldReturnProductById() {
        ProductCache productCache = new ProductCache();

        Product product = productCache.getProductById(1).orElseThrow();

        Assertions.assertEquals(new Product(1, "Dummy Laptop", ProdcutType.ELECTRONICS), product);
    }

    @Test
    void shouldReturnEmptyWhenProductIdDoesNotExist() {
        ProductCache productCache = new ProductCache();

        Assertions.assertTrue(productCache.getProductById(999).isEmpty());
    }

    @Test
    void shouldCreateProductWithGeneratedId() {
        ProductCache productCache = new ProductCache();

        Product createdProduct = productCache.createProduct(new Product(null, "Gaming Mouse", ProdcutType.ELECTRONICS));

        Assertions.assertEquals(5, createdProduct.productid());
        Assertions.assertEquals("Gaming Mouse", createdProduct.name());
        Assertions.assertTrue(productCache.getProductById(5).isPresent());
        Assertions.assertEquals(5, productCache.getProducts().size());
    }

    @Test
    void shouldUpdateExistingProduct() {
        ProductCache productCache = new ProductCache();

        Product updatedProduct = productCache.updateProduct(2, new Product(null, "Organic Milk", ProdcutType.GROCERY))
                .orElseThrow();

        Assertions.assertEquals(new Product(2, "Organic Milk", ProdcutType.GROCERY), updatedProduct);
        Assertions.assertEquals(updatedProduct, productCache.getProductById(2).orElseThrow());
    }

    @Test
    void shouldReturnEmptyWhenUpdatingMissingProduct() {
        ProductCache productCache = new ProductCache();

        Assertions.assertTrue(productCache.updateProduct(999, new Product(null, "Unknown", ProdcutType.FASHION)).isEmpty());
    }

    @Test
    void shouldDeleteExistingProduct() {
        ProductCache productCache = new ProductCache();

        boolean deleted = productCache.deleteProduct(3);

        Assertions.assertTrue(deleted);
        Assertions.assertTrue(productCache.getProductById(3).isEmpty());
        Assertions.assertEquals(3, productCache.getProducts().size());
    }

    @Test
    void shouldReturnFalseWhenDeletingMissingProduct() {
        ProductCache productCache = new ProductCache();

        Assertions.assertFalse(productCache.deleteProduct(999));
        Assertions.assertEquals(4, productCache.getProducts().size());
    }
}
