package com.a03.silk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.JurusanKursus;
import com.a03.silk.model.Siswa;

@Repository
public interface SiswaDb extends JpaRepository<Siswa, Long> {
    List<Siswa> findByIsDeletedFalse();

    @Query("SELECT s FROM Siswa s JOIN s.tanggalKursusPerTahun t WHERE s.jurusanKursus = ?1 AND KEY(t) = ?2")
    List<Siswa> findByJurusanKursusAndTahun(JurusanKursus jurusanKursus, Integer tahun);
    
    List<Siswa> findByJurusanKursusIdJurusanKursus(long idJurusan);
}

