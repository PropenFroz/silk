package com.a03.silk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.dto.request.CreateEntryGajiGuruDetailRequestDTO;
import com.a03.silk.dto.request.CreateEntryGajiGuruRequestDTO;
import com.a03.silk.model.EntryGajiGuru;
import com.a03.silk.model.EntryGajiGuruDetail;
import com.a03.silk.repository.EntryGajiGuruDb;
import com.a03.silk.repository.GradeKursusDb;
import com.a03.silk.repository.GuruDb;
import com.a03.silk.repository.JurusanKursusDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EntryGajiGuruService {
    
    @Autowired
    EntryGajiGuruDb entryGajiGuruDb;

    @Autowired
    JurusanKursusDb jurusanKursusDb;

    @Autowired
    GradeKursusDb gradeKursusDb;

    @Autowired
    GuruDb guruDb;

    public EntryGajiGuru createEntryGajiGuru(CreateEntryGajiGuruRequestDTO createEntryGajiGuruRequestDTO) {
        var entryGajiGuru = new EntryGajiGuru();

        entryGajiGuru.setGuru(guruDb.findById(createEntryGajiGuruRequestDTO.getIdGuru()).get());
        entryGajiGuru.setJurusanKursus(jurusanKursusDb.findById(createEntryGajiGuruRequestDTO.getIdJurusanKursus()).get());

        List<EntryGajiGuruDetail> entryGajiGuruDetails = new ArrayList<>();
        List<CreateEntryGajiGuruDetailRequestDTO> listCreateEntryGajiGuruDetailRequestDTO = createEntryGajiGuruRequestDTO.getListCreateEntryGajiGuruDetailRequestDTO();
        
        for (CreateEntryGajiGuruDetailRequestDTO item : listCreateEntryGajiGuruDetailRequestDTO) {
            EntryGajiGuruDetail detail = new EntryGajiGuruDetail();
            detail.setMurid(item.getMurid());
            detail.setGradeKursus(gradeKursusDb.findById(item.getIdGradeKursus()).get());
            detail.setUangKursus(item.getUangKursus());
            detail.setTanggal(item.getTanggal());
            detail.setMinggu1(item.getMinggu1());
            detail.setMinggu2(item.getMinggu2());
            detail.setMinggu3(item.getMinggu3());
            detail.setMinggu4(item.getMinggu4());
            detail.setFeeGuru(item.getFeeGuru());
            detail.setKeterangan(item.getKeterangan());
            detail.setEntryGajiGuru(entryGajiGuru);
            entryGajiGuruDetails.add(detail);
        }
        entryGajiGuru.setDaftarEntryGajiGuruDetail(entryGajiGuruDetails);
        return entryGajiGuruDb.save(entryGajiGuru);
    }

    public List<EntryGajiGuru> getAllEntryGajiGuru() {
        return entryGajiGuruDb.findAll();
    }
}
