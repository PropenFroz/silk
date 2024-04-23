package com.a03.silk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.Guru;
import com.a03.silk.repository.GuruDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GuruService {
    
    @Autowired
    GuruDb guruDb;

    public Guru createGuru(Guru guru) {
        return guruDb.save(guru);
    }

    public List<Guru> getAllGuru() {
        return guruDb.findAll();
    }

    public Guru getGuruByUserId(long userId) {
        return guruDb.findByUserId(userId);
    }
}
