package com.iReserve.seeder;

import com.iReserve.entity.Role;
import com.iReserve.entity.User;
import com.iReserve.repository.RoleRepository;
import com.iReserve.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
       if (userRepository.count() == 0) {
           new RoleSeeder(roleRepository).run();
           seedUser();
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