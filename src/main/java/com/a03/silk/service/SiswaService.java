package com.a03.silk.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.dto.request.LaporanDataSiswaDTO;
import com.a03.silk.dto.request.UpdateStatusSiswaRequestDTO;
import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.model.JurusanKursus;
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

    public List<Siswa> getAllSiswaByJurusan(long idJurusan) {
        return siswaDb.findByJurusanKursusIdJurusanKursus(idJurusan);
    }

    public Siswa getSiswaById(long idSiswa) {
        return siswaDb.findById(idSiswa).get();
    }
    
    public Siswa updateStatusSiswa(UpdateStatusSiswaRequestDTO updateStatusSiswaRequestDTO) {
        var siswa = siswaDb.findById(updateStatusSiswaRequestDTO.getIdSiswa()).get();
        siswa.setStatus(updateStatusSiswaRequestDTO.getStatus());
        return siswaDb.save(siswa);
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

    public long countByStatusAndJurusanKursus(int status, JurusanKursus jurusan) {
        return siswaDb.countByStatusAndJurusanKursus(status, jurusan);
    }

    public List<LaporanDataSiswaDTO> getDataJumlahSiswaByTahun(int tahun) {
        List<LaporanDataSiswaDTO> laporanSiswa = new ArrayList<>();
    
        String[] namaBulan = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        };
    
        // Inisialisasi array untuk menyimpan jumlah siswa setiap bulan
        int[] jumlahSiswaBaruBulan = new int[13];
        int[] jumlahSiswaCutiBulan = new int[13];
        int[] jumlahSiswaCutiMasukKembaliBulan = new int[13];
        int[] jumlahSiswaOffBulan = new int[13];
        int[] jumlahTotalSiswaAktif = new int[13];
    
        for (int bulan = 1; bulan <= 12; bulan++) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, tahun);
            cal.set(Calendar.MONTH, bulan - 1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date startDate = cal.getTime();
            System.out.println(startDate.toString());
            
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            Date endDate = cal.getTime();
            System.out.println(endDate.toString());

            // Menghitung jumlah siswa baru, cuti, cuti kembali, dan off di setiap bulan
            int jumlahSiswaBaru = siswaDb.countByTanggalDaftarBetweenAndStatus(startDate, endDate, 1);
            int jumlahSiswaCuti = siswaDb.countByTanggalDaftarBetweenAndStatus(startDate, endDate, 2);
            int jumlahSiswaCutiMasukKembali = siswaDb.countByTanggalDaftarBetweenAndStatus(startDate, endDate, 3);
            int jumlahSiswaOff = siswaDb.countByTanggalDaftarBetweenAndStatus(startDate, endDate, 4);
    
            // Akumulasi jumlah siswa untuk bulan yang sedang diproses
            jumlahSiswaBaruBulan[bulan] = jumlahSiswaBaru;

            jumlahSiswaCutiBulan[bulan] = jumlahSiswaCuti;

            jumlahSiswaCutiMasukKembaliBulan[bulan] = jumlahSiswaCutiMasukKembali;

            jumlahSiswaOffBulan[bulan] = jumlahSiswaOff;

            jumlahTotalSiswaAktif[bulan] = jumlahSiswaBaru + jumlahSiswaCutiMasukKembali + jumlahTotalSiswaAktif[bulan -1];
        }
    

      
        // Buat DTO untuk setiap bulan dan tambahkan ke dalam list
        for (int i = 0; i < 12; i++) {
            LaporanDataSiswaDTO dto = new LaporanDataSiswaDTO();
            dto.setBulan(namaBulan[i]);
            dto.setJumlahSiswaBaru(jumlahSiswaBaruBulan[i+1]);
            dto.setJumlahSiswaCuti(jumlahSiswaCutiBulan[i+1]);
            dto.setJumlahSiswaCutiMasukKembali(jumlahSiswaCutiMasukKembaliBulan[i+1]);
            dto.setJumlahSiswaOff(jumlahSiswaOffBulan[i+1]);
            dto.setJumlahTotalSiswaAktif(jumlahTotalSiswaAktif[i+1]);
            laporanSiswa.add(dto);
        }
    
        return laporanSiswa;
    }
    
}