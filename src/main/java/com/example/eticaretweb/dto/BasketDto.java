package com.example.eticaretweb.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private Long basketId;
    private int status;
    private double totalPrice;
    private UserDto user=new UserDto();
    private List<BasketProductDto> basketProductDtoList;
    private int count;
}
