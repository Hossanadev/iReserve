package com.iReserve.seeder;

import com.iReserve.entity.Role;
import com.iReserve.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
       if (roleRepository.count() == 0) {
           seedRole();
       }
    }

    private void seedRole() {
        Role role1 = new Role();
        Role role2 = new Role();

        role1.setName("ADMIN");
        role2.setName("USER");

        roleRepository.save(role1);
        roleRepository.save(role2);
    }
}