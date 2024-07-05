package com.example.eticaretweb.repository;

import com.example.eticaretweb.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {


    Optional<Basket> findByUser_UserIdAndStatusEquals(Long userId, int status);

}
