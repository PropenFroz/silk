package com.a03.silk.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.request.CreateEntryGajiGuruRequestDTO;
import com.a03.silk.model.EntryGajiGuru;
import com.a03.silk.model.EntryGajiGuruDetail;
import com.a03.silk.service.EntryGajiGuruService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class EntryGajiGuruController {

    @Autowired
    EntryGajiGuruService entryGajiGuruService;

    @PostMapping("/entry-gaji-guru")
    public EntryGajiGuru createEntryGajiGuru(@RequestBody CreateEntryGajiGuruRequestDTO createEntryGajiGuruRequestDTO) {        
        return entryGajiGuruService.createEntryGajiGuru(createEntryGajiGuruRequestDTO);
    }
    
    @GetMapping("/entry-gaji-guru/all")
    public List<EntryGajiGuru> getAllEntryGajiGuru() {
        return entryGajiGuruService.getAllEntryGajiGuru();
    }
    
    @GetMapping("/entry-gaji-guru/filter")
    public List<EntryGajiGuruDetail> getEntryGajiGuruByFilter(
        @RequestParam("idGuru") long idGuru, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
        return entryGajiGuruService.filterEntryGajiGuru(idGuru, startDate, endDate);
    }

    @DeleteMapping("/entry-gaji-guru-detail/delete/{id}")
    public EntryGajiGuruDetail deleteEntryTransaksiSiswa(@PathVariable("id") long id) {
        var entryGajiGuruDetail = entryGajiGuruService.getEntryGajiGuruDetailById(id);
        entryGajiGuruService.deleteEntryGajiGuruDetail(entryGajiGuruDetail);
        return entryGajiGuruDetail;
    }
}
