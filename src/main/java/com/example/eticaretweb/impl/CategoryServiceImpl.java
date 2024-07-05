package com.example.eticaretweb.impl;

import com.example.eticaretweb.dto.CategoryDto;
import com.example.eticaretweb.entity.Category;
import com.example.eticaretweb.exception.RecordNotFoundExceptions;
import com.example.eticaretweb.repository.CategoryRepository;
import com.example.eticaretweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = toEntity(categoryDto);
        category = categoryRepository.save(category);
        return toDto(category);
    }

    @Transactional
    @Override
    public CategoryDto getById(Long id) {
         return toDto(categoryRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return List.of();
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        return null;
    }



    public Category findById(Long id) {
       return   categoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundExceptions(4000,"Category not found"));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }


    public CategoryDto toDto(Category category) {
        category.setProductList(category.getProductList());
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }
}
