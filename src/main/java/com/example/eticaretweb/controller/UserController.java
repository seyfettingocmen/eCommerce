package com.example.eticaretweb.controller;

import com.example.eticaretweb.dto.UserDto;
import com.example.eticaretweb.request.UserRequest;
import com.example.eticaretweb.response.UserResponse;
import com.example.eticaretweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest){

        return toResponse(userService.create(toDto(userRequest)));
    }
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable String id){
        return toResponse(userService.findByUserId(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        userService.deleteByUserId(id);
    }
    @GetMapping
    public List<UserResponse> findAll(){
        return userService.getAll();
    }
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable String id, @RequestBody UserRequest userRequest){
        return toResponse(userService.update(id,toDto(userRequest)));
    }
    public UserResponse toResponse(UserDto userDto){
        return UserResponse.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto toDto(UserRequest userRequest){
        return UserDto.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .userName(userRequest.getUserName())
                .password(userRequest.getPassword())
                .build();
    }
}
