package com.a03.silk.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.EntryPendaftaran;
import com.a03.silk.repository.EntryPendaftaranDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PendaftaranSiswaService {

    @Autowired
    EntryPendaftaranDb entryPendaftaranDb;

    public EntryPendaftaran createEntryPendaftaranSiswa(EntryPendaftaran entryPendaftaran) {
        return entryPendaftaranDb.save(entryPendaftaran);
    }

    public List<EntryPendaftaran> getAllEntryPendaftaranSiswa() {
        return entryPendaftaranDb.findAll();
    }

    public List<EntryPendaftaran> getEntryPendaftaranSiswaByDate(Date startDate, Date endDate) {
        return entryPendaftaranDb.findByTanggalPembayaranBetween(startDate, endDate);
    }
}
