package com.microsevices.io.controller;

import com.microsevices.io.dto.ProductCreateRequest;
import com.microsevices.io.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductCreateRequest request){
        productService.createProduct(request);
    }

}
