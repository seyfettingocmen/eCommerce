package com.example.eticaretweb.service;

import com.example.eticaretweb.dto.ProductDto;
import com.example.eticaretweb.entity.Product;
import com.example.eticaretweb.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto updateById(ProductDto productDto);
    ProductDto get(Long id);
    void deleteById(Long id);

    Product findById(Long id);

    List<ProductResponse> getAll();
}
