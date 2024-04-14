package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.model.JurusanKursus;

import java.util.List;
import java.util.Date;

@Repository
public interface EntryTransaksiSiswaDb extends JpaRepository<EntryTransaksiSiswa, Long> {
    List<EntryTransaksiSiswa> findByTanggalPembayaranBetweenOrderByTanggalPembayaranAsc(Date startDate, Date endDate);

    List<EntryTransaksiSiswa> findByTanggalPembayaranBetweenAndIsDeletedOrderByTanggalPembayaranAsc(Date startDate, Date endDate, boolean isDeleted);

    List<EntryTransaksiSiswa> findByTanggalPembayaranBetweenAndIsDeletedAndSiswaJurusanKursusOrderByTanggalPembayaranAsc(Date startDate, Date endDate, boolean isDeleted, JurusanKursus jurusan);

}