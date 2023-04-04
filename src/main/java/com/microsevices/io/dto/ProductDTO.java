package com.microsevices.io.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private boolean active;
}
