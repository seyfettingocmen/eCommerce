package com.example.eticaretweb.response;

import com.example.eticaretweb.dto.BasketProductDto;
import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketResponse extends BaseResponse{
    private Long basketId;
    private int status;
    private double totalPrice;
    private Long userId;
    private List<BasketProductDto> basketProductDtoList;
}
