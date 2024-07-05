package com.example.eticaretweb.dto;

import com.example.eticaretweb.entity.BasketProduct;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketProductDto {
    private Long basketProductId;
    private int count;
    private double totalAmount;
    private Long productId;
    private Long basketId;

    public BasketProductDto(Long productId,int count){
        this.productId=productId;
        this.count=count;
    }
}
