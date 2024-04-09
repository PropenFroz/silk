package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.Guru;

@Repository
public interface GuruDb extends JpaRepository<Guru, Long> {
    
}
