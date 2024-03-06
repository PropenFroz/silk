package com.a03.silk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.BukuPurwacaraka;
import com.a03.silk.repository.BukuPurwacarakaDb;

import jakarta.transaction.Transactional;

import java.util.*;

@Service
@Transactional
public class BukuPurwacarakaService {

    @Autowired
    private BukuPurwacarakaDb bukuPurwacarakaDb;

    public BukuPurwacaraka createBukuPurwacaraka(BukuPurwacaraka bukuPurwacaraka) {
        return bukuPurwacarakaDb.save(bukuPurwacaraka);
    }

    public List<BukuPurwacaraka> getAllBukuPurwacaraka() {
        return bukuPurwacarakaDb.findAll();
    }

    public BukuPurwacaraka getBukuPurwacarakaById(long idBukuPurwacaraka) {
        return bukuPurwacarakaDb.findById(idBukuPurwacaraka).get();
    }
}