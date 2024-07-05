package com.example.eticaretweb.controller;

import com.example.eticaretweb.dto.CategoryDto;
import com.example.eticaretweb.impl.ProductServiceImpl;
import com.example.eticaretweb.request.CategoryRequest;
import com.example.eticaretweb.response.CategoryResponse;
import com.example.eticaretweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductServiceImpl productService;

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

    public CategoryDto requestToDto(CategoryRequest categoryRequest) {
        return CategoryDto.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
    }
}
