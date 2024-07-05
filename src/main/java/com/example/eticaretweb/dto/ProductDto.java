package com.example.eticaretweb.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
