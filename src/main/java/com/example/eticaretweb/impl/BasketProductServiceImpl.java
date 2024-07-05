package com.example.eticaretweb.impl;

import com.example.eticaretweb.dto.BasketProductDto;
import com.example.eticaretweb.entity.Basket;
import com.example.eticaretweb.entity.BasketProduct;
import com.example.eticaretweb.entity.Product;
import com.example.eticaretweb.repository.BasketProductRepository;
import com.example.eticaretweb.service.BasketProductService;
import com.example.eticaretweb.service.BasketService;
import com.example.eticaretweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketProductServiceImpl implements BasketProductService {
    @Autowired
    BasketProductRepository basketProductRepository;
    @Autowired
    BasketService basketService;

    @Autowired
    ProductService productService;

    @Override
    public BasketProduct save(BasketProduct basketProduct) {
        return basketProductRepository.save(basketProduct);
    }

    @Override
    public BasketProduct findByBasketIdAndProductId(Long basketId, Long productId) {
        BasketProduct basketProduct=basketProductRepository.findBasketProductByBasket_BasketIdAndProduct_ProductId(basketId,productId);
        return basketProduct;
    }

    public  BasketProductDto toDto(BasketProduct basketProduct) {
        BasketProductDto basketProductDto = new BasketProductDto();
        basketProductDto.setBasketProductId(basketProduct.getBasketProductId());
        basketProductDto.setCount(basketProduct.getCount());
        basketProductDto.setTotalAmount(basketProduct.getTotalAmount());
        basketProductDto.setProductId(basketProduct.getProduct().getProductId());
        basketProductDto.setBasketId(basketProduct.getBasket().getBasketId());
        return basketProductDto;
    }
    private BasketProduct toBasketProduct(BasketProductDto basketProductDto) {
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setBasketProductId(basketProductDto.getBasketProductId());
        basketProduct.setCount(basketProductDto.getCount());
        basketProduct.setTotalAmount(basketProductDto.getTotalAmount());

        // Product ve Basket nesnelerini oluşturup atamalarını gerçekleştiriyoruz
        Product product = productService.findById(basketProductDto.getProductId());
        Basket basket = basketService.findById(basketProductDto.getBasketId());

        basketProduct.setProduct(product);
        basketProduct.setBasket(basket);

        return basketProduct;
    }

}
