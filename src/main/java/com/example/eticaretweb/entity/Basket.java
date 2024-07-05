package com.example.eticaretweb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long basketId;
    private int status;
    private double totalPrice;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "basket" ,cascade = CascadeType.ALL)
    private List<BasketProduct> basketProductList;

}
