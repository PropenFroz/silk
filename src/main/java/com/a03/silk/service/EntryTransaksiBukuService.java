package com.a03.silk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.BukuPurwacaraka;
import com.a03.silk.model.EntryTransaksiBuku;
import com.a03.silk.repository.BukuPurwacarakaDb;
import com.a03.silk.repository.EntryTransaksiBukuDb;

import jakarta.transaction.Transactional;

import java.util.*;;

@Service
@Transactional
public class EntryTransaksiBukuService {

    @Autowired
    EntryTransaksiBukuDb entryTransaksiBukuDb;

    @Autowired
    BukuPurwacarakaDb bukuPurwacarakaDb;

    long previousStock = 0;

    public EntryTransaksiBuku createEntryTransaksiBuku(EntryTransaksiBuku entryTransaksiBuku) {
        EntryTransaksiBuku entry = entryTransaksiBukuDb.save(entryTransaksiBuku);
        savePreviousStock(entry);
        BukuPurwacaraka bukuToUpdate = bukuPurwacarakaDb.findByIdBukuPurwacaraka(entry.getBukuPurwacaraka().getIdBukuPurwacaraka());
        bukuToUpdate.setJumlah(bukuToUpdate.getJumlah() + entry.getJumlahBeli() - entry.getJumlahJual()); 
        return entryTransaksiBukuDb.save(entry);
    }

    private void savePreviousStock(EntryTransaksiBuku entry) {
        previousStock = entry.getJumlah();
    }

    public List<EntryTransaksiBuku> getAllEntryTransaksiBuku() {
        return entryTransaksiBukuDb.findAll();
    }

    public List<EntryTransaksiBuku> getEntryBukuByDate(Date startDate, Date endDate) {
        return entryTransaksiBukuDb.findByTanggalBeliAndTanggalJualBetween(startDate, endDate);
    }

    public EntryTransaksiBuku updateEntryTransaksiBuku(Long idEntryBuku, EntryTransaksiBuku updatedEntry) {
        EntryTransaksiBuku entryToUpdate = entryTransaksiBukuDb.findById(idEntryBuku).get();

        entryToUpdate.setJumlah(updatedEntry.getJumlah());
        entryToUpdate.setTanggalBeli(updatedEntry.getTanggalBeli());
        entryToUpdate.setJumlahBeli(updatedEntry.getJumlahBeli());
        entryToUpdate.setTanggalJual(updatedEntry.getTanggalJual());
        entryToUpdate.setJumlahJual(updatedEntry.getJumlahJual());
        entryToUpdate.setSisa(updatedEntry.getSisa());
        entryToUpdate.setHargaBeli(updatedEntry.getHargaBeli());
        entryToUpdate.setHargaJual(updatedEntry.getHargaJual());
    
        BukuPurwacaraka bukuToUpdate = bukuPurwacarakaDb.findByIdBukuPurwacaraka(entryToUpdate.getBukuPurwacaraka().getIdBukuPurwacaraka());
        bukuToUpdate.setJumlah((int) previousStock + entryToUpdate.getJumlahBeli() - entryToUpdate.getJumlahJual());
        bukuPurwacarakaDb.save(bukuToUpdate);

        return entryTransaksiBukuDb.save(entryToUpdate);
    }

    public void deleteEntryTransaksiBuku(Long idEntryBuku) {
        EntryTransaksiBuku entryToDelete = entryTransaksiBukuDb.findById(idEntryBuku)
                .orElseThrow(() -> new RuntimeException("Entry Transaksi Buku not found with id: " + idEntryBuku));
        BukuPurwacaraka bukuToUpdate = bukuPurwacarakaDb.findByIdBukuPurwacaraka(entryToDelete.getBukuPurwacaraka().getIdBukuPurwacaraka());
        bukuToUpdate.setJumlah(bukuToUpdate.getJumlah() - entryToDelete.getJumlahBeli() + entryToDelete.getJumlahJual());
        bukuPurwacarakaDb.save(bukuToUpdate);
        entryTransaksiBukuDb.deleteById(idEntryBuku);
    }
    
}
