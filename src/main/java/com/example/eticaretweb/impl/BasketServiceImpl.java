package com.example.eticaretweb.impl;

import com.example.eticaretweb.dto.BasketDto;
import com.example.eticaretweb.dto.BasketProductDto;
import com.example.eticaretweb.entity.Basket;
import com.example.eticaretweb.entity.BasketProduct;
import com.example.eticaretweb.repository.BasketRepository;
import com.example.eticaretweb.service.BasketService;
import com.example.eticaretweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    BasketProductServiceImpl basketProductService;
    @Autowired
    ProductService productService;

    private final int BASKET_STATUS_NONE = 0;
    private final int BASKET_STATUS_SALED = 1;

    @Override
    @Transactional
    public BasketDto create(BasketDto basketDto) {
        Optional<Basket> basket = basketRepository.findByUser_UserIdAndStatusEquals(basketDto.getUser().getUserId(), BASKET_STATUS_NONE);
        if (basket.isPresent()) {
            return haveBasketAddNew(basket.get(), basketDto);
        } else {
            return nullBasketAddProduct(basketDto);
        }
    }

    @Override
    public BasketDto update(String id, BasketDto basketDto) {
        Basket basket = basketRepository.findById(Long.parseLong(id)).orElse(null);

        basket.setStatus(basketDto.getStatus());
        basket.setTotalPrice(basketDto.getTotalPrice());
        basket.setUser(userService.toEntity(basketDto.getUser()));

        // BasketDto'daki BasketProduct listesini BasketProductDto listesini çeviren method
        List<BasketProduct> basketProductList = new ArrayList<>();
        for (BasketProductDto basketProductDto : basketDto.getBasketProductDtoList()) {
            BasketProduct newBasketProduct = new BasketProduct();
            newBasketProduct.setBasketProductId(basketProductDto.getBasketProductId());
            newBasketProduct.setCount(basketProductDto.getCount());
            newBasketProduct.getProduct().setProductId(basketProductDto.getProductId());
            newBasketProduct.setTotalAmount(basketProductDto.getTotalAmount());
            newBasketProduct.setBasketProductId(basketProductDto.getBasketProductId());
            basketProductList.add(newBasketProduct);
        }
        basket.setBasketProductList(basketProductList);
        Basket updatedBasket = basketRepository.save(basket);
        return toDto(updatedBasket);
    }


    @Override
    public BasketDto getByID(Long id) {
        return toDto(Objects.requireNonNull(basketRepository.findById(id).orElse(null)));
    }

    @Override
    public List<BasketDto> getAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        basketRepository.deleteById(id);
    }

    @Override
    public Basket findById(Long basketId) {
        return basketRepository.findById(basketId).orElseThrow();
    }

    /*
    haveBasketAddNew metodu, mevcut bir sepete yeni ürünler ekler.
    Eğer ürün sepette yoksa yeni bir BasketProduct oluşturur ve sepete ekler;
    eğer ürün zaten varsa, ürünün miktarını artırır ve toplam fiyatı günceller.
    ,Son olarak, sepetin toplam fiyatını hesaplar ve sepeti BasketDto nesnesine dönüştürerek geri döner.
     */

    private BasketDto haveBasketAddNew(Basket basket, BasketDto basketDto) {
        List<BasketProduct> basketProductList = basket.getBasketProductList();
        BasketProduct basketProduct = basketProductService.findByBasketIdAndProductId(basket.getBasketId(), basketDto.getBasketProductDtoList().get(0).getProductId());
        if (basketProduct == null) {
            BasketProduct basketProduct1 = new BasketProduct();

            for (BasketProductDto basketProductDto : basketDto.getBasketProductDtoList()) {
                basketProduct1.setProduct(productService.findById(basketProductDto.getProductId()));
                basketProduct1.setCount(basketProductDto.getCount());
                basketProduct1.setBasket(basket);
                basketProduct1.setTotalAmount(totalHesaplama(basketProduct1.getProduct().getPrice(), basketProduct1.getCount()));
                basketProductList.add(basketProduct1);
            }
        } else {
            for (BasketProductDto basketProductDto : basketDto.getBasketProductDtoList()) {
                basketProduct.setCount(basketProduct.getCount() + basketProductDto.getCount());
                basketProduct.setTotalAmount(totalHesaplama(basketProduct.getProduct().getPrice(), basketProduct.getCount()));
            }
        }
        basket.setTotalPrice(basketTotalPrice(basketProductList));
        basket.setBasketProductList(basketProductList);
        return toDto(basket);
    }


    /*
     Aşağıda ki method, BasketDto nesnesinden bir Basket nesnesi oluşturur. Bu metod, kullanıcı bilgilerini
     ve ürünleri içeren boş bir sepet oluşturur, sepetin ürünlerini ve toplam fiyatını hesaplar,
     ardından bu sepeti veritabanına kaydeder ve Basket nesnesini BasketDto nesnesine dönüştürerek geri döner.
     */
    private BasketDto nullBasketAddProduct(BasketDto basketDto) {
        Basket basket = new Basket();
        basket.setUser(userService.findById(basketDto.getUser().getUserId()));
        basket.setStatus(BASKET_STATUS_NONE);
        List<BasketProduct> basketProductList = new ArrayList<>();
        for (BasketProductDto basketProductDto : basketDto.getBasketProductDtoList()) {
            BasketProduct basketProduct = new BasketProduct();
            basketProduct.setBasket(basket);
            basketProduct.setCount(basketProductDto.getCount());
            basketProduct.setProduct(productService.findById(basketProductDto.getProductId()));
            basketProduct.setTotalAmount(totalHesaplama(basketProduct.getProduct().getPrice(), basketProduct.getCount()));
            basketProductList.add(basketProduct);
        }
        basket.setBasketProductList(basketProductList);
        basket.setTotalPrice(basketTotalPrice(basketProductList));
        return toDto(basket);

    }

    private Double basketTotalPrice(List<BasketProduct> basketProductList) {
        double totalPrice = 0;
        for (BasketProduct basketProduct : basketProductList) {
            totalPrice += basketProduct.getTotalAmount();
        }
        return totalPrice;
    }

    private Double totalHesaplama(Double price, Integer count) {
        return price * count;
    }

    private BasketDto toDto(Basket basket) {
        List<BasketProductDto> basketProductDtoList = new ArrayList<>();
        for (BasketProduct basketProduct : basket.getBasketProductList()) {
            BasketProductDto basketProductDto = basketProductService.toDto(basketProduct);

            basketProductDtoList.add(basketProductDto);
        }
        return BasketDto.builder()
                .basketId(basket.getBasketId())
                .status(basket.getStatus())
                .totalPrice(basket.getTotalPrice())
                .user(userService.findByUserId(String.valueOf(basket.getUser().getUserId())))
                .basketProductDtoList(basketProductDtoList)
                .build();
    }

}
