package com.example.eticaretweb.impl;

import com.example.eticaretweb.dto.UserDto;
import com.example.eticaretweb.entity.User;
import com.example.eticaretweb.exception.RecordNotFoundExceptions;
import com.example.eticaretweb.repository.UserRepository;
import com.example.eticaretweb.response.UserResponse;
import com.example.eticaretweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        User user = toEntity(userDto);
        user = userRepository.save(user);
        return toDto(user);
    }

    @Override
    public UserDto findByUserId(String id) {
        return toDto(userRepository.findById(Long.parseLong(id)).orElse(null));
    }

    @Override
    public void deleteByUserId(String userId) {
        userRepository.deleteById(Long.parseLong(userId));
    }

    @Override
    public UserDto update(String id, UserDto userDto) {
        User user = userRepository.findById(Long.parseLong(id)).orElse(null);
        assert user != null;
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setSurname(userDto.getSurname());

        User updatedUser = userRepository.save(user);
        return toDto(updatedUser);
    }

    @Override
    public List<UserResponse> getAll() {

        return entityListToResponseList(userRepository.findAll());
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundExceptions(5000,"Kullanicı bulunamadı"));
        return user;
    }

    public List<UserResponse> entityListToResponseList(List<User> userList) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : userList) {
            UserResponse userResponse = new UserResponse();
            userResponse.setUserId(user.getUserId());
            userResponse.setName(user.getName());
            userResponse.setSurname(user.getSurname());
            userResponse.setPassword(user.getPassword());
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    public User toEntity(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .build();
    }

}

