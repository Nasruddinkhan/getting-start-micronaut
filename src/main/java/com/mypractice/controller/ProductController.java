package com.mypractice.controller;

import com.mypractice.model.Product;
import com.mypractice.service.ProductService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

import java.util.List;

@Controller("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Get
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @Get("/{id}")
    public HttpResponse<Product> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Post
    public HttpResponse<Product> createProduct(@Body Product product) {
        return HttpResponse.created(productService.createProduct(product));
    }

    @Put("/{id}")
    public HttpResponse<Product> updateProduct(@PathVariable Integer id, @Body Product product) {
        return productService.updateProduct(id, product)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteProduct(@PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return HttpResponse.noContent();
        }
        return HttpResponse.notFound();
    }
}
