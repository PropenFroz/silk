package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.GuruJurusan;

@Repository
public interface GuruJurusanDb extends JpaRepository<GuruJurusan, Long>{
    
}
