package com.microsevices.io.service;

import com.microsevices.io.dto.ProductCreateRequest;
import com.microsevices.io.model.Product;
import com.microsevices.io.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductCreateRequest request) {
        log.info("Saving Product {} in createProduct [STARTED]", request.getName());
        Product product = Product
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .unitPrice(request.getUnitPrice())
                .isActive(request.isActive())
                .build();
        productRepository.save(product);
        log.info("Saved Product {} with id {} in createProduct [END]", product.getName(), product.getId());
    }

}
