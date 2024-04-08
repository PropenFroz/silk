package com.a03.silk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.dto.request.CreateEntryKursusSiswaRequestDTO;
import com.a03.silk.dto.request.CreateEntryLainnyaSiswaRequestDTO;
import com.a03.silk.dto.request.CreateEntryTransaksiSiswaRequestDTO;
import com.a03.silk.dto.request.UpdateEntryTransaksiSiswaRequestDTO;
import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.model.Siswa;
import com.a03.silk.repository.EntryTransaksiSiswaDb;
import com.a03.silk.repository.GradeKursusDb;
import com.a03.silk.repository.JurusanKursusDb;
import com.a03.silk.repository.SiswaDb;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class EntryTransaksiSiswaService {

    @Autowired
    EntryTransaksiSiswaDb entryTransaksiSiswaDb;

    @Autowired
    JurusanKursusDb jurusanKursusDb;

    @Autowired
    GradeKursusDb gradeKursusDb;

    @Autowired
    SiswaDb siswaDb;

    public EntryTransaksiSiswa createEntryTransaksiSiswaDaftar(CreateEntryTransaksiSiswaRequestDTO createEntryTransaksiSiswaRequestDTO) {
        var entryTransaksiSiswa = new EntryTransaksiSiswa();
        entryTransaksiSiswa.setJenisTransaksi(createEntryTransaksiSiswaRequestDTO.getJenisTransaksi());
        entryTransaksiSiswa.setTanggalPembayaran(createEntryTransaksiSiswaRequestDTO.getTanggalPembayaran());

        var siswa = new Siswa();
        siswa.setJurusanKursus(jurusanKursusDb.findById(createEntryTransaksiSiswaRequestDTO.getJurusanKursus()).get());
        siswa.setGradeKursus(gradeKursusDb.findById(createEntryTransaksiSiswaRequestDTO.getGradeKursus()).get());
        siswa.setNamaSiswa(createEntryTransaksiSiswaRequestDTO.getNamaSiswa());
        siswa.setTanggalDaftar(createEntryTransaksiSiswaRequestDTO.getTanggalPembayaran());

        entryTransaksiSiswa.setSiswa(siswa);
        entryTransaksiSiswa.setUangPendaftaran(createEntryTransaksiSiswaRequestDTO.getUangPendaftaran());
        entryTransaksiSiswa.setUangKursus(createEntryTransaksiSiswaRequestDTO.getUangKursus());
        entryTransaksiSiswa.setUangBuku(createEntryTransaksiSiswaRequestDTO.getUangBuku());
        entryTransaksiSiswa.setCash(createEntryTransaksiSiswaRequestDTO.getCash());
        entryTransaksiSiswa.setTransfer(createEntryTransaksiSiswaRequestDTO.getTransfer());
        entryTransaksiSiswa.setKeterangan(createEntryTransaksiSiswaRequestDTO.getKeterangan());

        siswaDb.save(siswa);
        return entryTransaksiSiswaDb.save(entryTransaksiSiswa);
    }

    public EntryTransaksiSiswa createEntryTransaksiSiswaKursus(CreateEntryKursusSiswaRequestDTO createEntryKursusSiswaRequestDTO) {
        var entryTransaksiSiswa = new EntryTransaksiSiswa();
        entryTransaksiSiswa.setJenisTransaksi(createEntryKursusSiswaRequestDTO.getJenisTransaksi());
        entryTransaksiSiswa.setTanggalPembayaran(createEntryKursusSiswaRequestDTO.getTanggalPembayaran());

        var siswa = siswaDb.findById(createEntryKursusSiswaRequestDTO.getSiswa()).get();

        List<Date> tanggalKursus = new ArrayList<>();

        if (siswa.getTanggalKursusPerTahun().get(createEntryKursusSiswaRequestDTO.getTahunKursus()) == null || siswa.getTanggalKursusPerTahun().get(createEntryKursusSiswaRequestDTO.getTahunKursus()).isEmpty()) {
            tanggalKursus.add(createEntryKursusSiswaRequestDTO.getTanggalPembayaran());
        } 
        else {
            for (Date date : siswa.getTanggalKursusPerTahun().get(createEntryKursusSiswaRequestDTO.getTahunKursus())) {
                tanggalKursus.add(date);
            }
            tanggalKursus.add(createEntryKursusSiswaRequestDTO.getTanggalPembayaran());
        }

        siswa.getTanggalKursusPerTahun().put(createEntryKursusSiswaRequestDTO.getTahunKursus(), tanggalKursus);

        entryTransaksiSiswa.setSiswa(siswa);
        entryTransaksiSiswa.setUangPendaftaran(createEntryKursusSiswaRequestDTO.getUangPendaftaran());
        entryTransaksiSiswa.setUangKursus(createEntryKursusSiswaRequestDTO.getUangKursus());
        entryTransaksiSiswa.setUangBuku(createEntryKursusSiswaRequestDTO.getUangBuku());
        entryTransaksiSiswa.setCash(createEntryKursusSiswaRequestDTO.getCash());
        entryTransaksiSiswa.setTransfer(createEntryKursusSiswaRequestDTO.getTransfer());
        entryTransaksiSiswa.setKeterangan(createEntryKursusSiswaRequestDTO.getKeterangan());
    
        siswaDb.save(siswa);
        return entryTransaksiSiswaDb.save(entryTransaksiSiswa);
    }

    public EntryTransaksiSiswa createEntryTransaksiSiswaLainnya(CreateEntryLainnyaSiswaRequestDTO createEntryLainnyaSiswaRequestDTO) {
        var entryTransaksiSiswa = new EntryTransaksiSiswa();
        entryTransaksiSiswa.setJenisTransaksi(createEntryLainnyaSiswaRequestDTO.getJenisTransaksi());
        entryTransaksiSiswa.setTanggalPembayaran(createEntryLainnyaSiswaRequestDTO.getTanggalPembayaran());

        var siswa = siswaDb.findById(createEntryLainnyaSiswaRequestDTO.getSiswa()).get();

        entryTransaksiSiswa.setSiswa(siswa);
        entryTransaksiSiswa.setUangPendaftaran(createEntryLainnyaSiswaRequestDTO.getUangPendaftaran());
        entryTransaksiSiswa.setUangKursus(createEntryLainnyaSiswaRequestDTO.getUangKursus());
        entryTransaksiSiswa.setUangBuku(createEntryLainnyaSiswaRequestDTO.getUangBuku());
        entryTransaksiSiswa.setCash(createEntryLainnyaSiswaRequestDTO.getCash());
        entryTransaksiSiswa.setTransfer(createEntryLainnyaSiswaRequestDTO.getTransfer());
        entryTransaksiSiswa.setKeterangan(createEntryLainnyaSiswaRequestDTO.getKeterangan());
    
        siswaDb.save(siswa);
        return entryTransaksiSiswaDb.save(entryTransaksiSiswa);
    }



    public EntryTransaksiSiswa updateEntry(UpdateEntryTransaksiSiswaRequestDTO updateEntryTransaksiSiswaFromDTO){
        EntryTransaksiSiswa entryTransaksiSiswa = getEntryTransaksiSiswaById(updateEntryTransaksiSiswaFromDTO.getIdEntryTransaksiSiswa());

        var siswa = siswaDb.findById(updateEntryTransaksiSiswaFromDTO.getSiswa()).get();
        List<Date> tanggalKursus = siswa.getTanggalKursusPerTahun().get(updateEntryTransaksiSiswaFromDTO.getTahunKursus());

        if (entryTransaksiSiswa.getJenisTransaksi() == 1) {
            siswa.setTanggalDaftar(updateEntryTransaksiSiswaFromDTO.getTanggalPembayaran());
        }
        else if (entryTransaksiSiswa.getJenisTransaksi() == 2) {
            for (int i = 0 ; i < tanggalKursus.size(); i++) {
                if (tanggalKursus.get(i).equals(entryTransaksiSiswa.getTanggalPembayaran())) {
                    tanggalKursus.set(i, updateEntryTransaksiSiswaFromDTO.getTanggalPembayaran());
                }
                else {
                    continue;
                }
            }
            siswa.getTanggalKursusPerTahun().put(updateEntryTransaksiSiswaFromDTO.getTahunKursus(), tanggalKursus);
        }

        entryTransaksiSiswa.setTanggalPembayaran(updateEntryTransaksiSiswaFromDTO.getTanggalPembayaran());
        entryTransaksiSiswa.setUangPendaftaran(updateEntryTransaksiSiswaFromDTO.getUangPendaftaran());
        entryTransaksiSiswa.setUangKursus(updateEntryTransaksiSiswaFromDTO.getUangKursus());
        entryTransaksiSiswa.setUangBuku(updateEntryTransaksiSiswaFromDTO.getUangBuku());
        entryTransaksiSiswa.setCash(updateEntryTransaksiSiswaFromDTO.getCash());
        entryTransaksiSiswa.setTransfer(updateEntryTransaksiSiswaFromDTO.getTransfer());
        entryTransaksiSiswa.setKeterangan(updateEntryTransaksiSiswaFromDTO.getKeterangan());
        entryTransaksiSiswa.setSiswa(siswa);

        siswaDb.save(siswa);
        entryTransaksiSiswaDb.save(entryTransaksiSiswa);
        return entryTransaksiSiswa;
    }

    public List<EntryTransaksiSiswa> getAllEntryTransaksiSiswa() {
        return entryTransaksiSiswaDb.findAll();
    }

    public List<EntryTransaksiSiswa> getEntryTransaksiSiswaByDate(Date startDate, Date endDate) {
        return entryTransaksiSiswaDb.findByTanggalPembayaranBetweenAndIsDeletedOrderByTanggalPembayaranAsc(startDate, endDate, false);
    }

    public EntryTransaksiSiswa getEntryTransaksiSiswaById(long id){
        for(EntryTransaksiSiswa entryTransaksiSiswa : getAllEntryTransaksiSiswa()) {
            if (entryTransaksiSiswa.getIdEntryTransaksiSiswa() == id){
                return entryTransaksiSiswa;
            }
        }
        return null;
    }

    public void deleteEntryTransaksiSiswa(EntryTransaksiSiswa entryTransaksiSiswa) {
        entryTransaksiSiswa.setIsDeleted(true);

        if (entryTransaksiSiswa.getJenisTransaksi() == 1) {
            var siswa = siswaDb.findById(entryTransaksiSiswa.getSiswa().getIdSiswa()).get();
            siswa.setDeleted(true);
            siswaDb.save(siswa);
        }

        if (entryTransaksiSiswa.getJenisTransaksi() == 2) {
            var siswa = siswaDb.findById(entryTransaksiSiswa.getSiswa().getIdSiswa()).get();
            for (Entry<Integer, List<Date>> entry : siswa.getTanggalKursusPerTahun().entrySet()) {
                List<Date> list = entry.getValue();
                if (list.contains(entryTransaksiSiswa.getTanggalPembayaran())) {
                    list.remove(entryTransaksiSiswa.getTanggalPembayaran());
                }
            }
    
            siswaDb.save(siswa);  
        }
        entryTransaksiSiswaDb.save(entryTransaksiSiswa);
    }
}