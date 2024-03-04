package com.a03.silk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.repository.EntryTransaksiSiswaDb;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Date;

@Service
@Transactional
public class EntryTransaksiSiswaService {

    @Autowired
    EntryTransaksiSiswaDb entryTransaksiSiswaDb;

    public EntryTransaksiSiswa createEntryTransaksiSiswa(EntryTransaksiSiswa entryTransaksiSiswa) {
        return entryTransaksiSiswaDb.save(entryTransaksiSiswa);
    }

    public List<EntryTransaksiSiswa> getAllEntryTransaksiSiswa() {
        return entryTransaksiSiswaDb.findAll();
    }

    public List<EntryTransaksiSiswa> getEntryTransaksiSiswaByDate(Date startDate, Date endDate) {
        return entryTransaksiSiswaDb.findByTanggalPembayaranBetweenOrderByTanggalPembayaranAsc(startDate, endDate);
    }
}
