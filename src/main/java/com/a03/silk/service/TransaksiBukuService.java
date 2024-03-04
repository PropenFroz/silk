package com.a03.silk.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.EntryBuku;
import com.a03.silk.repository.EntryTransaksiBukuDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransaksiBukuService {

    @Autowired
    EntryTransaksiBukuDb entryTransaksiBukuDb;

    public EntryBuku createEntryTransaksiBuku(EntryBuku entryBuku) {
        return entryTransaksiBukuDb.save(entryBuku);
    }

    public List<EntryBuku> getAllEntryTransaksiBuku() {
        return entryTransaksiBukuDb.findAll();
    }
}
