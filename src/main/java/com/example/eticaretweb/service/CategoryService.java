package com.example.eticaretweb.service;

import com.example.eticaretweb.dto.CategoryDto;
import com.example.eticaretweb.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto getById(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto update(CategoryDto categoryDto);

    void deleteById(Long id);
}
