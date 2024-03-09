package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.EntryTransaksiBukuMapper;
import com.a03.silk.dto.request.CreateEntryTransaksiBukuRequestDTO;
import com.a03.silk.dto.request.UpdateEntryTransaksiBukuRequestDTO;
import com.a03.silk.dto.response.ReadEntryTransaksiBukuResponseDTO;
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
        return entryTransaksiBukuService.createEntryTransaksiBuku(createEntryTransaksiBukuRequestDTO);
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

    @PutMapping("/entry-transaksi-buku/update/{id}")
    public EntryTransaksiBuku updateEntryTransaksiSiswa(@RequestBody UpdateEntryTransaksiBukuRequestDTO entryTransaksiBukuDTO, @PathVariable("id") long idEntryBuku){
        entryTransaksiBukuDTO.setIdEntryBuku(idEntryBuku);
        return entryTransaksiBukuService.updateEntryTransaksiBuku(entryTransaksiBukuDTO);
    }

    @DeleteMapping("/entry-transaksi-buku/delete/{id}")
    public void deleteEntryTransaksiBuku(@PathVariable("id") Long idEntryBuku) {
        entryTransaksiBukuService.deleteEntryTransaksiBuku(idEntryBuku);
    }

    @GetMapping("/entry-transaksi-buku/get/{id}")
    public ReadEntryTransaksiBukuResponseDTO getEntryTransaksiBukuById(@PathVariable("id") Long idEntryBuku) {
        var entryBuku = entryTransaksiBukuService.getEntryTransaksiBukuById(idEntryBuku);
        ReadEntryTransaksiBukuResponseDTO entryDTO = new ReadEntryTransaksiBukuResponseDTO();
        entryDTO.setBukuPurwacaraka(entryBuku.getBukuPurwacaraka().getIdBukuPurwacaraka());
        entryDTO.setHargaBeli(entryBuku.getHargaBeli());
        entryDTO.setHargaJual(entryBuku.getHargaJual());
        entryDTO.setTanggalBeli(entryBuku.getTanggalBeli());
        entryDTO.setTanggalJual(entryBuku.getTanggalJual());
        entryDTO.setJumlahBeli(entryBuku.getJumlahBeli());
        entryDTO.setJumlahJual(entryBuku.getJumlahJual());
        return entryDTO;

    }





    

}