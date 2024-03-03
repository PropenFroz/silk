package com.a03.silk.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.KursusSiswaMapper;
import com.a03.silk.dto.request.CreateEntryKursusSiswaRequestDTO;
import com.a03.silk.model.EntryKursus;
import com.a03.silk.service.KursusSiswaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class KursusSiswaController {
    
    @Autowired
    KursusSiswaMapper kursusSiswaMapper;
    
    @Autowired
    KursusSiswaService kursusSiswaService;

    @PostMapping("/entry-kursus")
    public EntryKursus createEntryKursusSiswa(@RequestBody CreateEntryKursusSiswaRequestDTO createEntryKursusSiswaRequestDTO) {
        var entryKursus = kursusSiswaMapper.toEntryKursus(createEntryKursusSiswaRequestDTO);
        return kursusSiswaService.createEntryKursusSiswa(entryKursus);
    }

    @GetMapping("/entry-kursus/all")
    public List<EntryKursus> getAllEntryKursusSiswa() {
        return kursusSiswaService.getAllEntryKursusSiswa();
    }

    @GetMapping("/entry-kursus/filter-by-date")
        public List<EntryKursus> getEntryKursusSiswaByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
        return kursusSiswaService.getEntryKursusSiswaByDate(startDate, endDate);
    }
}
