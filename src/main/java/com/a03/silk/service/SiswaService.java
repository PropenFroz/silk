package com.a03.silk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.Siswa;
import com.a03.silk.repository.SiswaDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SiswaService {

    @Autowired
    SiswaDb siswaDb;

    public List<Siswa> getAllSiswa() {
        return siswaDb.findByIsDeletedFalse();
    }
    
}
