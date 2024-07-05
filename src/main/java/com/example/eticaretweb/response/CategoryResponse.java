package com.example.eticaretweb.response;

import com.example.eticaretweb.dto.ProductDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse extends BaseResponse {
    private Long categoryId;
    private String name;
    private String description;
    private List<ProductDto> productList;
}
