package com.iReserve.service;

import com.iReserve.dto.UserDto;
import com.iReserve.entity.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);
    User findUserByUsername(String username);
    List<User> findAllUsers();
    String createUser(UserDto user);
    String updateUser(UserDto user, Long userId);
    String deleteUser(Long userId);
}