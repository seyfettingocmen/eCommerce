package com.example.eticaretweb.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long basketProductId;
    private int count;
    private double totalAmount;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;

}
