package com.example.eticaretweb.repository;

import com.example.eticaretweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
