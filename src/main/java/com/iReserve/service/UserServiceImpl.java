package com.iReserve.service;

import com.iReserve.dto.UserDto;
import com.iReserve.entity.User;
import com.iReserve.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String createUser(UserDto userDto) {
        User user = findUserByUsername(userDto.getUsername());
        if (user != null) {
            return "User already exists";
        }
        user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled((short) 1);
        userRepository.save(user);
        return userDto.getUsername() + ", your account has been created";
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
