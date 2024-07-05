package com.example.eticaretweb.service;

import com.example.eticaretweb.dto.UserDto;
import com.example.eticaretweb.entity.User;
import com.example.eticaretweb.response.UserResponse;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto findByUserId(String userId);
    void deleteByUserId(String userId);
    UserDto update(String id,UserDto userDto);
    List<UserResponse> getAll();
    User findById(Long id);
}
