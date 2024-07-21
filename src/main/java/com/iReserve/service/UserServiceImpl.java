package com.iReserve.service;

import com.iReserve.dto.UserDto;
import com.iReserve.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public String createUser(UserDto user) {
        return "";
    }

    @Override
    public String updateUser(UserDto user, Long userId) {
        return "";
    }

    @Override
    public String deleteUser(Long userId) {
        return "";
    }
}
