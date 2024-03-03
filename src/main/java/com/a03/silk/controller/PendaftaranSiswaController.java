package com.a03.silk.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.PendaftaranSiswaMapper;
import com.a03.silk.dto.request.CreateEntryPendaftaranSiswaRequestDTO;
import com.a03.silk.model.EntryPendaftaran;
import com.a03.silk.service.PendaftaranSiswaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class PendaftaranSiswaController {
    
    @Autowired
    PendaftaranSiswaService pendaftaranSiswaService;

    @Autowired
    PendaftaranSiswaMapper pendaftaranSiswaMapper;

    @PostMapping("/entry-pendaftaran")
    public EntryPendaftaran createEntryPendaftaranSiswa(@RequestBody CreateEntryPendaftaranSiswaRequestDTO createEntryPendaftaranSiswaRequestDTO) {
        var entryPendaftaran = pendaftaranSiswaMapper.toEntryPendaftaran(createEntryPendaftaranSiswaRequestDTO);
        return pendaftaranSiswaService.createEntryPendaftaranSiswa(entryPendaftaran);
    }

    @GetMapping("/entry-pendaftaran/all")
    public List<EntryPendaftaran> getAllEntryPendaftaranSiswa() {
        return pendaftaranSiswaService.getAllEntryPendaftaranSiswa();
    }
    
    @GetMapping("/entry-pendaftaran/filter-by-date")
    public List<EntryPendaftaran> getEntryPendaftaranSiswaByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
        return pendaftaranSiswaService.getEntryPendaftaranSiswaByDate(startDate, endDate);
    }
}
