package com.a03.silk.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.EntryKursus;
import com.a03.silk.repository.EntryKursusDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class KursusSiswaService {
    
    @Autowired
    EntryKursusDb entryKursusDb;

    public EntryKursus createEntryKursusSiswa(EntryKursus EntryKursus) {
        return entryKursusDb.save(EntryKursus);
    }

    public List<EntryKursus> getAllEntryKursusSiswa() {
        return entryKursusDb.findAll();
    }

    public List<EntryKursus> getEntryKursusSiswaByDate(Date startDate, Date endDate) {
        return entryKursusDb.findByTanggalPembayaranBetween(startDate, endDate);
    }
}