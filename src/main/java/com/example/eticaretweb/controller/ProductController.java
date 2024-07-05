package com.example.eticaretweb.controller;

import com.example.eticaretweb.dto.ProductDto;
import com.example.eticaretweb.request.ProductRequest;
import com.example.eticaretweb.response.ProductResponse;
import com.example.eticaretweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest productRequest) {
        return toResponse(productService.save(toDto(productRequest)));
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return toResponse(productService.updateById(toDto(productRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }


    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return toResponse(productService.get(id));
    }


    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }


    public ProductResponse toResponse(ProductDto productDto) {
        return ProductResponse.builder()
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .categoryId(productDto.getCategoryId())
                .price(productDto.getPrice())
                .build();
    }

    public ProductDto toDto(ProductRequest productRequest) {
        return ProductDto.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .categoryId(productRequest.getCategoryId())
                .price(productRequest.getPrice())
                .build();
    }
}
