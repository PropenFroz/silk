package com.a03.silk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.model.Siswa;
import com.a03.silk.repository.JurusanKursusDb;
import com.a03.silk.repository.SiswaDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SiswaService {

    @Autowired
    SiswaDb siswaDb;

    @Autowired
    JurusanKursusDb jurusanKursusDb;

    public List<Siswa> getAllSiswa() {
        return siswaDb.findByIsDeletedFalse();
    }

    public Siswa getSiswaById(long idSiswa) {
        return siswaDb.findById(idSiswa).get();
    }

    public void updateIdPendaftaran(long idSiswa, long idEntry) {
        var siswa = siswaDb.findById(idSiswa).get();
        siswa.setIdPendaftaran(idEntry);
        siswaDb.save(siswa);
    }

    public void updateKursus(Siswa siswa, EntryTransaksiSiswa entryTransaksiSiswa, int tahunKursus) {
        if (siswa.getTanggalKursusPerTahun().get(tahunKursus) == null || siswa.getTanggalKursusPerTahun().get(tahunKursus).isEmpty()) {
            List<Long> tanggalKursus = new ArrayList<>();
            tanggalKursus.add(entryTransaksiSiswa.getIdEntryTransaksiSiswa());
            siswa.getTanggalKursusPerTahun().put(tahunKursus, tanggalKursus);
        } 
        else {
            List<Long> tanggalKursus = siswa.getTanggalKursusPerTahun().get(tahunKursus);
            tanggalKursus.add(entryTransaksiSiswa.getIdEntryTransaksiSiswa());
            siswa.getTanggalKursusPerTahun().put(tahunKursus, tanggalKursus);
        }
    }

    public List<Siswa> getIuranSiswaByTahunJurusan(long idJurusan, int tahun) {
        var jurusanKursus = jurusanKursusDb.findById(idJurusan).get();
        return siswaDb.findByJurusanKursusAndTahun(jurusanKursus, tahun);
    }
}