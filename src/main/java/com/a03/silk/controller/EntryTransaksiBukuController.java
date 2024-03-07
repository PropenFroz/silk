package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.EntryTransaksiBukuMapper;
import com.a03.silk.dto.request.CreateEntryTransaksiBukuRequestDTO;
import com.a03.silk.model.EntryTransaksiBuku;
import com.a03.silk.service.BukuPurwacarakaService;
import com.a03.silk.service.EntryTransaksiBukuService;


import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class EntryTransaksiBukuController {

    @Autowired
    EntryTransaksiBukuService entryTransaksiBukuService;

    @Autowired
    BukuPurwacarakaService bukuPurwacarakaService;

    @Autowired
    EntryTransaksiBukuMapper entryTransaksiBukuMapper;

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


}