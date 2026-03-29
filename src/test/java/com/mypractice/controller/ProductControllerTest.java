package com.mypractice.controller;

import com.mypractice.model.ProdcutType;
import com.mypractice.model.Product;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
class ProductControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void shouldReturnProductsFromEndpoint() {
        List<Product> products = httpClient.toBlocking()
                .retrieve(HttpRequest.GET("/products"), Argument.listOf(Product.class));

        Assertions.assertEquals(4, products.size());
        Assertions.assertEquals("Dummy Laptop", products.getFirst().name());
        Assertions.assertEquals("Air Fryer", products.getLast().name());
    }

    @Test
    void shouldReturnProductByIdFromEndpoint() {
        Product product = httpClient.toBlocking()
                .retrieve(HttpRequest.GET("/products/1"), Product.class);

        Assertions.assertEquals(1, product.productid());
        Assertions.assertEquals("Dummy Laptop", product.name());
    }

    @Test
    void shouldCreateProductFromEndpoint() {
        Product request = new Product(null, "Monitor", ProdcutType.ELECTRONICS);

        HttpResponse<Product> response = httpClient.toBlocking()
                .exchange(HttpRequest.POST("/products", request), Product.class);

        Assertions.assertEquals(201, response.code());
        Assertions.assertNotNull(response.body());
        Assertions.assertNotNull(response.body().productid());
        Assertions.assertEquals("Monitor", response.body().name());
        Assertions.assertEquals(ProdcutType.ELECTRONICS, response.body().type());
    }

    @Test
    void shouldUpdateProductFromEndpoint() {
        Product created = httpClient.toBlocking()
                .retrieve(HttpRequest.POST("/products", new Product(null, "Speaker", ProdcutType.ELECTRONICS)), Product.class);

        Product updateRequest = new Product(null, "Bluetooth Speaker", ProdcutType.ELECTRONICS);

        HttpResponse<Product> response = httpClient.toBlocking()
                .exchange(HttpRequest.PUT("/products/" + created.productid(), updateRequest), Product.class);

        Assertions.assertEquals(200, response.code());
        Assertions.assertNotNull(response.body());
        Assertions.assertEquals(created.productid(), response.body().productid());
        Assertions.assertEquals("Bluetooth Speaker", response.body().name());
    }

    @Test
    void shouldDeleteProductFromEndpoint() {
        Product created = httpClient.toBlocking()
                .retrieve(HttpRequest.POST("/products", new Product(null, "Desk Lamp", ProdcutType.HOME_APPLIANCE)), Product.class);

        HttpResponse<?> deleteResponse = httpClient.toBlocking()
                .exchange(HttpRequest.DELETE("/products/" + created.productid()));

        Assertions.assertEquals(204, deleteResponse.code());

        HttpClientResponseException exception = Assertions.assertThrows(
                HttpClientResponseException.class,
                () -> httpClient.toBlocking().exchange(HttpRequest.GET("/products/" + created.productid()), Product.class)
        );
        Assertions.assertEquals(404, exception.getStatus().getCode());
    }

    @Test
    void shouldReturnNotFoundForMissingProduct() {
        HttpClientResponseException exception = Assertions.assertThrows(
                HttpClientResponseException.class,
                () -> httpClient.toBlocking().exchange(HttpRequest.GET("/products/9999"), Product.class)
        );

        Assertions.assertEquals(404, exception.getStatus().getCode());
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingMissingProduct() {
        Product updateRequest = new Product(null, "Unknown", ProdcutType.FASHION);

        HttpClientResponseException exception = Assertions.assertThrows(
                HttpClientResponseException.class,
                () -> httpClient.toBlocking().exchange(HttpRequest.PUT("/products/9999", updateRequest), Product.class)
        );

        Assertions.assertEquals(404, exception.getStatus().getCode());
    }

    @Test
    void shouldReturnNotFoundWhenDeletingMissingProduct() {
        HttpClientResponseException exception = Assertions.assertThrows(
                HttpClientResponseException.class,
                () -> httpClient.toBlocking().exchange(HttpRequest.DELETE("/products/9999"))
        );

        Assertions.assertEquals(404, exception.getStatus().getCode());
    }
}
