package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.LainnyaSiswaMapper;
import com.a03.silk.dto.request.CreateEntryLainnyaSiswaRequestDTO;
import com.a03.silk.model.EntryLainnya;
import com.a03.silk.service.LainnyaSiswaService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LainnyaSiswaController {
    
    @Autowired
    LainnyaSiswaService lainnyaSiswaService;

    @Autowired
    LainnyaSiswaMapper lainnyaSiswaMapper;

    @PostMapping("/entry-lainnya")
    public EntryLainnya createEntryLainnyaSiswa(@RequestBody CreateEntryLainnyaSiswaRequestDTO createEntryLainnyaSiswaRequestDTO) {
        var entryLainnya = lainnyaSiswaMapper.toEntryLainnya(createEntryLainnyaSiswaRequestDTO);
        return lainnyaSiswaService.createEntryLainnyaSiswa(entryLainnya);
    }

    @GetMapping("/entry-lainnya/all")
    public List<EntryLainnya> getAllEntryLainnyaSiswa() {
        return lainnyaSiswaService.getAllEntryLainnyaSiswa();
    }

    @GetMapping("/entry-lainnya/filter-by-date")
        public List<EntryLainnya> getEntryLainnyaSiswaByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
        return lainnyaSiswaService.getEntryLainnyaSiswaByDate(startDate, endDate);
    }
}
