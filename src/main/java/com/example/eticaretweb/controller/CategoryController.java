package com.example.eticaretweb.controller;

import com.example.eticaretweb.dto.CategoryDto;
import com.example.eticaretweb.dto.ProductDto;
import com.example.eticaretweb.entity.Category;
import com.example.eticaretweb.entity.Product;
import com.example.eticaretweb.request.CategoryRequest;
import com.example.eticaretweb.response.CategoryResponse;
import com.example.eticaretweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest categoryRequest) {
        return dtoToResponse(categoryService.save(requestToDto(categoryRequest)));
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return dtoToResponse(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        return dtoToResponse(categoryService.update(requestToDto(categoryRequest)));
    }

    public CategoryResponse dtoToResponse(CategoryDto categoryDto) {
        return CategoryResponse.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .productList(categoryDto.getProductList())
                .build();
    }

    public CategoryResponse entityToResponse(Category category) {

        List<ProductDto> productDtos = category.getProductList().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .productList(productDtos)
                .build();

    }

    private ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

    }

    public CategoryDto requestToDto(CategoryRequest categoryRequest) {
        return CategoryDto.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
    }
}
