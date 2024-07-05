package com.example.eticaretweb.impl;

import com.example.eticaretweb.dto.ProductDto;
import com.example.eticaretweb.entity.Product;
import com.example.eticaretweb.exception.RecordNotFoundExceptions;
import com.example.eticaretweb.repository.ProductRepository;
import com.example.eticaretweb.response.ProductResponse;
import com.example.eticaretweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryServiceImpl categoryService;


    @Transactional
    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = toEntity(productDto);
        product = repository.save(product);
        return toDto(product);
    }

    @Transactional
    @Override
    public ProductDto updateById(ProductDto productDto) {
        ProductDto newProductDto = new ProductDto();
        newProductDto.setProductId(productDto.getProductId());
        newProductDto.setName(productDto.getName());
        newProductDto.setPrice(productDto.getPrice());
        newProductDto.setDescription(productDto.getDescription());
        newProductDto.setCategoryId(productDto.getCategoryId());
        return toDto(repository.save(toEntity(newProductDto)));
    }

    @Override
    public ProductDto get(Long id) {
        return toDto(repository.findById(id).orElseThrow(() -> new RecordNotFoundExceptions(5000, "Product not found")));
    }

    @Transactional
    @Override
    public void deleteById(Long productId) {
        repository.deleteById(productId);
    }

    @Override
    public Product findById(Long id) {
        Product product = repository.findById(id).orElseThrow(RuntimeException::new);
        return product;
    }

    @Override
    public List<ProductResponse> getAll() {
        return entityListtoResponseList(repository.findAll());
    }


    public List<Product> responseListToEntityList(List<ProductResponse> productResponseList) {
        return productResponseList.stream()
                .map(this::responseToProduct)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> entityListtoResponseList(List<Product> productList) {
        return productList.stream()
                .map(this::convertToProductResponse)
                .collect(Collectors.toList());
    }


    private Product responseToProduct(ProductResponse productResponse) {

        return Product.builder()
                .productId(productResponse.getProductId())
                .name(productResponse.getName())
                .description(productResponse.getDescription())
                .price(productResponse.getPrice())
                .category(categoryService.findById(productResponse.getCategoryId()))
                .build();

    }

    private ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getProductId())
                .build();

    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .name(product.getName())
                .categoryId(product.getCategory().getCategoryId())
                .productId(product.getProductId())
                .price(product.getPrice())
                .build();
    }

    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .category(categoryService.findById(productDto.getCategoryId()))
                .price(productDto.getPrice())
                .build();
    }
}
