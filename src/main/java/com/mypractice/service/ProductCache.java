package com.mypractice.service;

import com.mypractice.model.ProdcutType;
import com.mypractice.model.Product;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class ProductCache {

    private final AtomicReference<List<Product>> products = new AtomicReference<>(
            List.of(
                    new Product(1, "Dummy Laptop", ProdcutType.ELECTRONICS),
                    new Product(2, "Fresh Milk", ProdcutType.GROCERY),
                    new Product(3, "Casual T-Shirt", ProdcutType.FASHION),
                    new Product(4, "Air Fryer", ProdcutType.HOME_APPLIANCE)
            )
    );
    private final AtomicInteger nextId = new AtomicInteger(5);

    public List<Product> getProducts() {
        return products.get();
    }

    public Optional<Product> getProductById(Integer id) {
        return products.get().stream()
                .filter(product -> Objects.equals(product.productid(), id))
                .findFirst();
    }

    public Product createProduct(Product product) {
        Product createdProduct = new Product(nextId.getAndIncrement(), product.name(), product.type());
        products.updateAndGet(existingProducts -> {
            List<Product> updatedProducts = new ArrayList<>(existingProducts);
            updatedProducts.add(createdProduct);
            return List.copyOf(updatedProducts);
        });
        return createdProduct;
    }

    public Optional<Product> updateProduct(Integer id, Product product) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isEmpty()) {
            return Optional.empty();
        }

        Product updatedProduct = new Product(id, product.name(), product.type());
        products.updateAndGet(existingProducts -> existingProducts.stream()
                .map(currentProduct -> Objects.equals(currentProduct.productid(), id) ? updatedProduct : currentProduct)
                .toList());
        return Optional.of(updatedProduct);
    }

    public boolean deleteProduct(Integer id) {
        List<Product> existingProducts = products.get();
        List<Product> updatedProducts = existingProducts.stream()
                .filter(product -> !Objects.equals(product.productid(), id))
                .toList();
        if (updatedProducts.size() == existingProducts.size()) {
            return false;
        }
        products.set(updatedProducts);
        return true;
    }
}
