package com.microsevices.io.service;

import com.microsevices.io.dto.ProductCreateRequest;
import com.microsevices.io.dto.ProductDTO;
import com.microsevices.io.mapper.ProductMapper;
import com.microsevices.io.model.Product;
import com.microsevices.io.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDTO createProduct(ProductCreateRequest request) {
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
        return productMapper.toDto(product);
    }

    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    public ProductDTO findProductById(String id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }
}
