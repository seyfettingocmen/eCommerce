package com.example.eticaretweb.service;

import com.example.eticaretweb.dto.BasketDto;
import com.example.eticaretweb.entity.Basket;

import java.util.List;

public interface BasketService {
    BasketDto create(BasketDto basketDto);

    BasketDto getByID(Long id);

    List<BasketDto> getAll();

    void deleteById(Long id);

    BasketDto update(String id,BasketDto basketDto);


    Basket findById(Long basketId);
}
