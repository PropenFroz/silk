package com.a03.silk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.JurusanKursus;
import com.a03.silk.repository.JurusanKursusDb;

import jakarta.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class JurusanKursusService {
    
    @Autowired
    JurusanKursusDb jurusanKursusDb;

    public JurusanKursus createJurusanKursus(JurusanKursus jurusanKursus) {
        return jurusanKursusDb.save(jurusanKursus);
    }

    public List<JurusanKursus> getAllJurusanKursus() {
        return jurusanKursusDb.findAll();
    }
}
