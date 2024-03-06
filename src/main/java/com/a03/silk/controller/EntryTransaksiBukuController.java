package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.EntryTransaksiBukuMapper;
import com.a03.silk.dto.request.CreateEntryTransaksiBukuRequestDTO;
import com.a03.silk.model.EntryTransaksiBuku;
import com.a03.silk.service.BukuPurwacarakaService;
import com.a03.silk.service.EntryTransaksiBukuService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class EntryTransaksiBukuController {

    @Autowired
    EntryTransaksiBukuService entryTransaksiBukuService;

    @Autowired
    EntryTransaksiBukuMapper entryTransaksiBukuMapper;

    @Autowired
    BukuPurwacarakaService bukuPurwacarakaService;

    @PostMapping("/entry-transaksi-buku")
    public EntryTransaksiBuku createEntryTransaksiBuku(@RequestBody CreateEntryTransaksiBukuRequestDTO createEntryTransaksiBukuRequestDTO) {
        var bukuPurwacaraka = bukuPurwacarakaService.getBukuPurwacarakaById(createEntryTransaksiBukuRequestDTO.getBukuPurwacaraka().getIdBukuPurwacaraka());
        createEntryTransaksiBukuRequestDTO.setBukuPurwacaraka(bukuPurwacaraka);
        var entryTransaksiBuku = entryTransaksiBukuMapper.toEntryBuku(createEntryTransaksiBukuRequestDTO);
        return entryTransaksiBukuService.createEntryTransaksiBuku(entryTransaksiBuku);
    }

    @GetMapping("/entry-transaksi-buku/all")
    public List<EntryTransaksiBuku> getAllEntryTransaksiBuku() {
        return entryTransaksiBukuService.getAllEntryTransaksiBuku();
    }

    @GetMapping("/entry-transaksi-buku/filter-by-date")
    public List<EntryTransaksiBuku> getEntryBukuByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
        return entryTransaksiBukuService.getEntryBukuByDate(startDate, endDate);
    }

}