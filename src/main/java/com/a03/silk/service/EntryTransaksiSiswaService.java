package com.a03.silk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.dto.request.CreateEntryTransaksiSiswaRequestDTO;
import com.a03.silk.dto.request.UpdateEntryTransaksiSiswaRequestDTO;
import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.repository.EntryTransaksiSiswaDb;
import com.a03.silk.repository.GradeKursusDb;
import com.a03.silk.repository.JurusanKursusDb;

import jakarta.transaction.Transactional;

import java.util.List;
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

    public EntryTransaksiSiswa createEntryTransaksiSiswa(CreateEntryTransaksiSiswaRequestDTO createEntryTransaksiSiswaRequestDTO) {
        var entryTransaksiSiswa = new EntryTransaksiSiswa();
        entryTransaksiSiswa.setJenisTransaksi(createEntryTransaksiSiswaRequestDTO.getJenisTransaksi());
        entryTransaksiSiswa.setTanggalPembayaran(createEntryTransaksiSiswaRequestDTO.getTanggalPembayaran());
        entryTransaksiSiswa.setNamaSiswa(createEntryTransaksiSiswaRequestDTO.getNamaSiswa());
        entryTransaksiSiswa.setJurusanKursus(jurusanKursusDb.findById(createEntryTransaksiSiswaRequestDTO.getJurusanKursus()).get());
        entryTransaksiSiswa.setGradeKursus(gradeKursusDb.findById(createEntryTransaksiSiswaRequestDTO.getGradeKursus()).get());
        entryTransaksiSiswa.setUangPendaftaran(createEntryTransaksiSiswaRequestDTO.getUangPendaftaran());
        entryTransaksiSiswa.setUangKursus(createEntryTransaksiSiswaRequestDTO.getUangKursus());
        entryTransaksiSiswa.setUangBuku(createEntryTransaksiSiswaRequestDTO.getUangBuku());
        entryTransaksiSiswa.setCash(createEntryTransaksiSiswaRequestDTO.getCash());
        entryTransaksiSiswa.setTransfer(createEntryTransaksiSiswaRequestDTO.getTransfer());
        entryTransaksiSiswa.setKeterangan(createEntryTransaksiSiswaRequestDTO.getKeterangan());
        return entryTransaksiSiswaDb.save(entryTransaksiSiswa);
    }

    public EntryTransaksiSiswa updateEntry(UpdateEntryTransaksiSiswaRequestDTO updateEntryTransaksiSiswaFromDTO){
        EntryTransaksiSiswa entryTransaksiSiswa = getEntryTransaksiSiswaById(updateEntryTransaksiSiswaFromDTO.getIdEntryTransaksiSiswa());

        entryTransaksiSiswa.setJenisTransaksi(updateEntryTransaksiSiswaFromDTO.getJenisTransaksi());
        entryTransaksiSiswa.setTanggalPembayaran(updateEntryTransaksiSiswaFromDTO.getTanggalPembayaran());
        entryTransaksiSiswa.setNamaSiswa(updateEntryTransaksiSiswaFromDTO.getNamaSiswa());
        entryTransaksiSiswa.setJurusanKursus(jurusanKursusDb.findById(updateEntryTransaksiSiswaFromDTO.getJurusanKursus()).get());
        entryTransaksiSiswa.setGradeKursus(gradeKursusDb.findById(updateEntryTransaksiSiswaFromDTO.getGradeKursus()).get());

        entryTransaksiSiswa.setUangPendaftaran(updateEntryTransaksiSiswaFromDTO.getUangPendaftaran());
        entryTransaksiSiswa.setUangKursus(updateEntryTransaksiSiswaFromDTO.getUangKursus());
        entryTransaksiSiswa.setUangBuku(updateEntryTransaksiSiswaFromDTO.getUangBuku());
        entryTransaksiSiswa.setCash(updateEntryTransaksiSiswaFromDTO.getCash());
        entryTransaksiSiswa.setTransfer(updateEntryTransaksiSiswaFromDTO.getTransfer());
        entryTransaksiSiswa.setKeterangan(updateEntryTransaksiSiswaFromDTO.getKeterangan());
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
        entryTransaksiSiswaDb.save(entryTransaksiSiswa);
    }

    
}
