package com.a03.silk.repository;

import com.a03.silk.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleDb extends JpaRepository<Role, Long> {
    List<Role> findAll();

    Optional<Role> findByRole(String role);
}