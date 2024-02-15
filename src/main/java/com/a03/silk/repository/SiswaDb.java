package com.a03.silk.repository;

import com.a03.silk.model.Siswa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiswaDb extends JpaRepository<Siswa, Long> {

}
