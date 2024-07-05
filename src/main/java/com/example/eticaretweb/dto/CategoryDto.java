package com.example.eticaretweb.dto;

import com.example.eticaretweb.entity.Product;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long categoryId;
    private String name;
    private String description;
    private List<ProductDto> productList;
}
