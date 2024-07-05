package com.example.eticaretweb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> productList;
}
