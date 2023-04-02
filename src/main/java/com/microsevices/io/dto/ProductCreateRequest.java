package com.microsevices.io.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreateRequest {

    private String name;
    private String description;
    private BigDecimal unitPrice;
    private boolean active;

}
