package com.a03.silk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.Siswa;

@Repository
public interface SiswaDb extends JpaRepository<Siswa, Long> {
    List<Siswa> findByIsDeletedFalse();
    List<Siswa> findByJurusanKursusIdJurusanKursus(long idJurusan);
}
