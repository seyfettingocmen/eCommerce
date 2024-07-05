package com.example.eticaretweb.controller;

import com.example.eticaretweb.dto.BasketDto;
import com.example.eticaretweb.dto.BasketProductDto;
import com.example.eticaretweb.request.BasketRequest;
import com.example.eticaretweb.response.BasketResponse;
import com.example.eticaretweb.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/baskets")
@RequiredArgsConstructor
public class BasketController {
    @Autowired
    BasketService basketService;

    @PostMapping
    public BasketResponse create(@RequestBody BasketRequest basketRequest) {
        return toResponse(basketService.create(toDto(basketRequest)));
    }

    @GetMapping("/{id}")
    public BasketResponse getById(@PathVariable Long id) {
        return toResponse(basketService.getByID(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        basketService.deleteById(id);
    }

    @PutMapping("/{id}")
    public BasketResponse update(@PathVariable String id, @RequestBody BasketRequest basketRequest) {
        return toResponse(basketService.update(id, toDto(basketRequest)));
    }


    private BasketResponse toResponse(BasketDto basketDto) {
        return BasketResponse.builder()
                .basketId(basketDto.getBasketId())
                .status(basketDto.getStatus())
                .totalPrice(basketDto.getTotalPrice())
                .userId(basketDto.getUser().getUserId())
                .basketProductDtoList(basketDto.getBasketProductDtoList())
                .build();
    }

    private BasketDto toDto(BasketRequest basketRequest) {
        List<BasketProductDto> basketProductDtoList = new ArrayList<>();
        basketProductDtoList.add(new BasketProductDto(basketRequest.getProductId(), basketRequest.getCount()));
        BasketDto basketDto = new BasketDto();
        basketDto.setBasketId(basketRequest.getProductId());
        basketDto.getUser().setUserId(basketRequest.getUserId());
        basketDto.setBasketProductDtoList(basketProductDtoList);
        return basketDto;
    }

}
