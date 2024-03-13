package com.a03.silk.repository;

import com.a03.silk.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

    boolean existsByUsername(String username);



}
