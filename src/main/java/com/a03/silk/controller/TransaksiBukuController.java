package com.a03.silk.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.TransaksiBukuMapper;
import com.a03.silk.dto.request.CreateEntryTransaksiBukuRequestDTO;
import com.a03.silk.model.EntryBuku;
import com.a03.silk.service.TransaksiBukuService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TransaksiBukuController {
    
    @Autowired
    TransaksiBukuService transaksiBukuService;

    @Autowired
    TransaksiBukuMapper transaksiBukuMapper;

    @PostMapping("/entry-transaksi-buku")
    public EntryBuku createEntryTransaksiBuku(@RequestBody CreateEntryTransaksiBukuRequestDTO createEntryTransaksiBukuRequestDTO) {
        var entryBuku = transaksiBukuMapper.toEntryBuku(createEntryTransaksiBukuRequestDTO);
        return transaksiBukuService.createEntryTransaksiBuku(entryBuku);
    }

    @GetMapping("/entry-transaksi-buku/all")
    public List<EntryBuku> getAllEntryTransaksiBuku() {
        return transaksiBukuService.getAllEntryTransaksiBuku();
    }
}

