package com.iReserve.repository;

import com.iReserve.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findFirstByOrderByIdAsc();
    Role findByName(String name);
}