package com.a03.silk.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.EntryLainnya;
import com.a03.silk.repository.EntryLainnyaDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LainnyaSiswaService {

    @Autowired
    EntryLainnyaDb entryLainnyaDb;

    public EntryLainnya createEntryLainnyaSiswa(EntryLainnya EntryLainnya) {
        return entryLainnyaDb.save(EntryLainnya);
    }

    public List<EntryLainnya> getAllEntryLainnyaSiswa() {
        return entryLainnyaDb.findAll();
    }

    public List<EntryLainnya> getEntryLainnyaSiswaByDate(Date startDate, Date endDate) {
        return entryLainnyaDb.findByTanggalPembayaranBetween(startDate, endDate);
    }
}
