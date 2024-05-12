package com.a03.silk.service;

import com.a03.silk.dto.request.CreateBukuPurwacarakaRequestDTO;
import com.a03.silk.model.JurusanKursus;
import com.a03.silk.repository.JurusanKursusDb;
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

    @Autowired
    private JurusanKursusDb jurusanKursusDb;

    public BukuPurwacaraka createBukuPurwacaraka(CreateBukuPurwacarakaRequestDTO createBukuPurwacarakaRequestDTO) {
        BukuPurwacaraka bukuPurwacaraka = new BukuPurwacaraka();
        bukuPurwacaraka.setNamaBuku(createBukuPurwacarakaRequestDTO.getNamaBuku());
        bukuPurwacaraka.setJumlah(createBukuPurwacarakaRequestDTO.getJumlah());
        JurusanKursus jurusanKursus = jurusanKursusDb.findById(createBukuPurwacarakaRequestDTO.getJurusanKursus()).get();
        bukuPurwacaraka.setJurusanKursus(jurusanKursus);

        return bukuPurwacarakaDb.save(bukuPurwacaraka);
    }

    public List<BukuPurwacaraka> getAllBukuPurwacaraka() {
        return bukuPurwacarakaDb.findAll();
    }

    public BukuPurwacaraka getBukuPurwacarakaById(long idBukuPurwacaraka) {
        return bukuPurwacarakaDb.findById(idBukuPurwacaraka).get();
    }
}