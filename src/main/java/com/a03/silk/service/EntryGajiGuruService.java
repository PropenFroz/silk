package com.a03.silk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.dto.request.CreateEntryGajiGuruDetailRequestDTO;
import com.a03.silk.dto.request.CreateEntryGajiGuruRequestDTO;
import com.a03.silk.dto.request.UpdateEntryGajiGuruRequestDTO;
import com.a03.silk.model.EntryGajiGuru;
import com.a03.silk.model.EntryGajiGuruDetail;
import com.a03.silk.model.GuruJurusan;
import com.a03.silk.repository.EntryGajiGuruDb;
import com.a03.silk.repository.EntryGajiGuruDetailDb;
import com.a03.silk.repository.GradeKursusDb;
import com.a03.silk.repository.GuruDb;
import com.a03.silk.repository.GuruJurusanDb;
import com.a03.silk.repository.JurusanKursusDb;
import com.a03.silk.repository.SiswaDb;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EntryGajiGuruService {
    
    @Autowired
    EntryGajiGuruDb entryGajiGuruDb;

    @Autowired
    EntryGajiGuruDetailDb entryGajiGuruDetailDb;

    @Autowired
    JurusanKursusDb jurusanKursusDb;

    @Autowired
    GradeKursusDb gradeKursusDb;

    @Autowired
    GuruDb guruDb;

    @Autowired
    GuruJurusanDb guruJurusanDb;

    @Autowired
    SiswaDb siswaDb;

    public EntryGajiGuru createEntryGajiGuru(CreateEntryGajiGuruRequestDTO createEntryGajiGuruRequestDTO) {
        var guruJurusan = new GuruJurusan();
        guruJurusan.setGuru(guruDb.findById(createEntryGajiGuruRequestDTO.getIdGuru()).get());
        guruJurusan.setJurusanKursus(jurusanKursusDb.findById(createEntryGajiGuruRequestDTO.getIdJurusanKursus()).get());
        guruJurusanDb.save(guruJurusan);

        var entryGajiGuru = new EntryGajiGuru();
        entryGajiGuru.setGuru(guruDb.findById(createEntryGajiGuruRequestDTO.getIdGuru()).get());
        entryGajiGuru.setJurusanKursus(jurusanKursusDb.findById(createEntryGajiGuruRequestDTO.getIdJurusanKursus()).get());

        List<EntryGajiGuruDetail> entryGajiGuruDetails = new ArrayList<>();
        List<CreateEntryGajiGuruDetailRequestDTO> listCreateEntryGajiGuruDetailRequestDTO = createEntryGajiGuruRequestDTO.getListCreateEntryGajiGuruDetailRequestDTO();
        
        for (CreateEntryGajiGuruDetailRequestDTO item : listCreateEntryGajiGuruDetailRequestDTO) {
            EntryGajiGuruDetail detail = new EntryGajiGuruDetail();
            detail.setSiswa(siswaDb.findById(item.getSiswa()).get());
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

    public List<EntryGajiGuruDetail> filterEntryGajiGuru(long idGuru, Date startDate, Date endDate) {
        var guru = guruDb.findById(idGuru).get();
        return entryGajiGuruDetailDb.findByEntryGajiGuru_GuruAndTanggalBetween(guru, startDate, endDate);
    }

    public void deleteEntryGajiGuruDetail(EntryGajiGuruDetail entryGajiGuruDetail) {
        entryGajiGuruDetailDb.delete(entryGajiGuruDetail);
    }

    public EntryGajiGuruDetail getEntryGajiGuruDetailById(long id) {
        return entryGajiGuruDetailDb.findById(id).get();
    }

    public EntryGajiGuruDetail updateEntry(UpdateEntryGajiGuruRequestDTO updateEntryGajiGuruFormDTO){
        EntryGajiGuruDetail entryGajiGuru = getEntryGajiGuruDetailById(updateEntryGajiGuruFormDTO.getIdEntryGajiGuruDetail());
        var siswa = siswaDb.findById(updateEntryGajiGuruFormDTO.getSiswa()).get();
        entryGajiGuru.setSiswa(siswa);
        entryGajiGuru.setUangKursus(updateEntryGajiGuruFormDTO.getUangKursus());
        entryGajiGuru.setTanggal(updateEntryGajiGuruFormDTO.getTanggal());
        entryGajiGuru.setMinggu1(updateEntryGajiGuruFormDTO.getMinggu1());
        entryGajiGuru.setMinggu2(updateEntryGajiGuruFormDTO.getMinggu2());
        entryGajiGuru.setMinggu3(updateEntryGajiGuruFormDTO.getMinggu3());
        entryGajiGuru.setMinggu4(updateEntryGajiGuruFormDTO.getMinggu4());
        entryGajiGuru.setFeeGuru(updateEntryGajiGuruFormDTO.getFeeGuru());
        entryGajiGuru.setKeterangan(updateEntryGajiGuruFormDTO.getKeterangan());
        siswaDb.save(siswa);
        entryGajiGuruDetailDb.save(entryGajiGuru);
        return entryGajiGuru;
    }

}
