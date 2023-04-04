package com.microsevices.io.mapper;

import com.microsevices.io.dto.ProductDTO;
import com.microsevices.io.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target="active", source="isActive")
    ProductDTO toDto(Product product);

}
