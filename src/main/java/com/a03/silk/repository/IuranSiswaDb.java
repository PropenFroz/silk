package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.model.IuranSiswa;
import com.a03.silk.model.Siswa;

@Repository
public interface IuranSiswaDb extends JpaRepository<IuranSiswa, Long>{    
    boolean existsBySiswaAndTahunAndBulan(Siswa siswa, int tahun, int bulan);

    IuranSiswa findByEntryKursus(EntryTransaksiSiswa entryTransaksiSiswa);
}
