package com.iReserve.seeder;

import com.iReserve.entity.Role;
import com.iReserve.entity.User;
import com.iReserve.repository.RoleRepository;
import com.iReserve.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
       if (userRepository.count() == 0) {
           seedUser();
       }
       User user = userRepository.findFirstByOrderByIdAsc();
       if (user.getRole() == null) {
           user.setRole(roleRepository.findFirstByOrderByIdAsc());
           userRepository.save(user);
       }
    }

    private void seedUser() {
        User user = new User();
        Role userRole = roleRepository.findFirstByOrderByIdAsc();
        user.setUsername("dev");
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode("123"));
        userRepository.save(user);
    }
}