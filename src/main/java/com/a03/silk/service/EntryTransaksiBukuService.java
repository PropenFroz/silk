package com.a03.silk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.dto.request.CreateEntryTransaksiBukuRequestDTO;
import com.a03.silk.dto.request.UpdateEntryTransaksiBukuRequestDTO;
import com.a03.silk.model.BukuPurwacaraka;
import com.a03.silk.model.EntryTransaksiBuku;
import com.a03.silk.repository.BukuPurwacarakaDb;
import com.a03.silk.repository.EntryTransaksiBukuDb;

import jakarta.transaction.Transactional;

import java.util.*;

@Service
@Transactional
public class EntryTransaksiBukuService {

    @Autowired
    EntryTransaksiBukuDb entryTransaksiBukuDb;

    @Autowired
    BukuPurwacarakaDb bukuPurwacarakaDb;

    public EntryTransaksiBuku createEntryTransaksiBuku(CreateEntryTransaksiBukuRequestDTO createEntryTransaksiBukuRequestDTO) {
        var entryTransaksiBuku = new EntryTransaksiBuku();
        BukuPurwacaraka bukuPurwacaraka = bukuPurwacarakaDb.findByIdBukuPurwacaraka(createEntryTransaksiBukuRequestDTO.getBukuPurwacaraka());
        entryTransaksiBuku.setJumlah(bukuPurwacaraka.getJumlah());
        bukuPurwacaraka.setJumlah(bukuPurwacaraka.getJumlah() + createEntryTransaksiBukuRequestDTO.getJumlahBeli() - createEntryTransaksiBukuRequestDTO.getJumlahJual()); 
        entryTransaksiBuku.setBukuPurwacaraka(bukuPurwacaraka);
        entryTransaksiBuku.setJumlahBeli(createEntryTransaksiBukuRequestDTO.getJumlahBeli());
        entryTransaksiBuku.setJumlahJual(createEntryTransaksiBukuRequestDTO.getJumlahJual());
        entryTransaksiBuku.setHargaBeli(createEntryTransaksiBukuRequestDTO.getHargaBeli());
        entryTransaksiBuku.setHargaJual(createEntryTransaksiBukuRequestDTO.getHargaJual());
        entryTransaksiBuku.setTanggalBeli(createEntryTransaksiBukuRequestDTO.getTanggalBeli());
        entryTransaksiBuku.setTanggalJual(createEntryTransaksiBukuRequestDTO.getTanggalJual());
        return entryTransaksiBukuDb.save(entryTransaksiBuku);
    }

    public List<EntryTransaksiBuku> getAllEntryTransaksiBuku() {
        return entryTransaksiBukuDb.findAll();
    }

    public List<EntryTransaksiBuku> getEntryBukuByDate(Date startDate, Date endDate) {
        return entryTransaksiBukuDb.findByTanggalBeliAndTanggalJualBetween(startDate, endDate);
    }

    public EntryTransaksiBuku updateEntryTransaksiBuku(UpdateEntryTransaksiBukuRequestDTO updateEntryTransaksiBukuFromDTO) {
        EntryTransaksiBuku entryToUpdate = getEntryTransaksiBukuById(updateEntryTransaksiBukuFromDTO.getIdEntryBuku());
        BukuPurwacaraka newBukuPurwacaraka = bukuPurwacarakaDb.findByIdBukuPurwacaraka(updateEntryTransaksiBukuFromDTO.getBukuPurwacaraka());

        entryToUpdate.setBukuPurwacaraka(newBukuPurwacaraka);
        entryToUpdate.setTanggalBeli(updateEntryTransaksiBukuFromDTO.getTanggalBeli());
        entryToUpdate.setJumlahBeli(updateEntryTransaksiBukuFromDTO.getJumlahBeli());
        entryToUpdate.setTanggalJual(updateEntryTransaksiBukuFromDTO.getTanggalJual());
        entryToUpdate.setJumlahJual(updateEntryTransaksiBukuFromDTO.getJumlahJual());
        entryToUpdate.setHargaBeli(updateEntryTransaksiBukuFromDTO.getHargaBeli());
        entryToUpdate.setHargaJual(updateEntryTransaksiBukuFromDTO.getHargaJual());
    
        BukuPurwacaraka bukuToUpdate = bukuPurwacarakaDb.findByIdBukuPurwacaraka(entryToUpdate.getBukuPurwacaraka().getIdBukuPurwacaraka());
        bukuToUpdate.setJumlah((int)entryToUpdate.getJumlah() + entryToUpdate.getJumlahBeli() - entryToUpdate.getJumlahJual());

        return entryTransaksiBukuDb.save(entryToUpdate);
    }

    public void deleteEntryTransaksiBuku(Long idEntryBuku) {
        EntryTransaksiBuku entryToDelete = entryTransaksiBukuDb.findById(idEntryBuku).get();
        BukuPurwacaraka bukuToUpdate = bukuPurwacarakaDb.findByIdBukuPurwacaraka(entryToDelete.getBukuPurwacaraka().getIdBukuPurwacaraka());
        bukuToUpdate.setJumlah(bukuToUpdate.getJumlah() - entryToDelete.getJumlahBeli() + entryToDelete.getJumlahJual());
        bukuPurwacarakaDb.save(bukuToUpdate);
        entryTransaksiBukuDb.deleteById(idEntryBuku);
    }

    public EntryTransaksiBuku getEntryTransaksiBukuById(Long idEntryBuku) {
        return entryTransaksiBukuDb.findById(idEntryBuku).get();
    }
    
}
