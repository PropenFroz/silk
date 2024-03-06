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

    public EntryTransaksiBuku createEntryTransaksiBuku(EntryTransaksiBuku entryTransaksiBuku) {
        EntryTransaksiBuku entry = entryTransaksiBukuDb.save(entryTransaksiBuku);
        
        BukuPurwacaraka bukuToUpdate = bukuPurwacarakaDb.findByIdBukuPurwacaraka(entry.getBukuPurwacaraka().getIdBukuPurwacaraka());
        bukuToUpdate.setJumlah(bukuToUpdate.getJumlah() + entry.getJumlahBeli() - entry.getJumlahJual());

        return entryTransaksiBukuDb.save(entry);
    }

    public List<EntryTransaksiBuku> getAllEntryTransaksiBuku() {
        return entryTransaksiBukuDb.findAll();
    }

    public List<EntryTransaksiBuku> getEntryBukuByDate(Date startDate, Date endDate) {
        return entryTransaksiBukuDb.findByTanggalBeliAndTanggalJualBetween(startDate, endDate);
    }
}
