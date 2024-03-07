package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.JurusanKursus;


@Repository
public interface JurusanKursusDb extends JpaRepository<JurusanKursus, Long> { 
    JurusanKursus findByIdJurusanKursus(long idJurusanKursus);
}
