package com.example.eticaretweb.repository;

import com.example.eticaretweb.entity.BasketProduct;
import com.example.eticaretweb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct,Long> {
    void deleteBasketProductByProduct_ProductId(Long productId);
    BasketProduct findBasketProductByBasket_BasketIdAndProduct_ProductId(Long basketId, Long productId);

}
