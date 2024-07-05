package com.example.eticaretweb.service;

import com.example.eticaretweb.dto.BasketProductDto;
import com.example.eticaretweb.entity.Basket;
import com.example.eticaretweb.entity.BasketProduct;

public interface BasketProductService {
    BasketProduct save(BasketProduct basketProduct);
    BasketProduct findByBasketIdAndProductId(Long basketId,Long productId);
}
