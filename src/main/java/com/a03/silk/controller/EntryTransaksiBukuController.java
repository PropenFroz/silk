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
import com.a03.silk.service.LaporanTransaksiBukuPDF;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @GetMapping("/entry-transaksi-buku/filter-by-date")
    public List<EntryTransaksiBuku> getEntryBukuByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
        return entryTransaksiBukuService.getEntryBukuByDate(startDate, endDate);
    }

    @GetMapping("/entry-transaksi-buku/laporan")
    public void generateLaporanTransaksi(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, 
            HttpServletResponse response) throws DocumentException, IOException {
        
        DateFormat dateString = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = dateString.format(startDate);
        String endDateStr = dateString.format(endDate);

        String title = startDateStr + " - " + endDateStr;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
        
        response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=LaporanTransaksiBuku_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

        List<EntryTransaksiBuku> entryTransaksiBukuList = entryTransaksiBukuService.getEntryBukuByDate(startDate, endDate);

        LaporanTransaksiBukuPDF laporanTransaksiBukuPDF = new LaporanTransaksiBukuPDF();
        laporanTransaksiBukuPDF.generateLaporanTransaksiBuku(response, title, entryTransaksiBukuList);
    }
}