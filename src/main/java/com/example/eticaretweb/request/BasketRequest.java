package com.example.eticaretweb.request;

import com.example.eticaretweb.dto.BasketProductDto;
import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketRequest {

    private Long userId;
    private Long productId;
    private int count;
}
