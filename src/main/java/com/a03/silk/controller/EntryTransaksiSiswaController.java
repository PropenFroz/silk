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

import com.a03.silk.dto.EntryTransaksiSiswaMapper;
import com.a03.silk.dto.request.CreateEntryTransaksiSiswaRequestDTO;
import com.a03.silk.model.EntryTransaksiSiswa;
import com.a03.silk.service.EntryTransaksiSiswaService;
import com.a03.silk.service.LaporanTransaksiSiswaPDF;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class EntryTransaksiSiswaController {

    @Autowired
    EntryTransaksiSiswaService entryTransaksiSiswaService;

    @Autowired
    EntryTransaksiSiswaMapper entryTransaksiSiswaMapper;
    
    @PostMapping("/entry-transaksi-siswa")
    public EntryTransaksiSiswa createEntryKursusSiswa(@RequestBody CreateEntryTransaksiSiswaRequestDTO createEntryTransaksiSiswaRequestDTO) {
        var entryTransaksiSiswa = entryTransaksiSiswaMapper.toEntryTransaksiSiswa(createEntryTransaksiSiswaRequestDTO);
        return entryTransaksiSiswaService.createEntryTransaksiSiswa(entryTransaksiSiswa);
    }

    @GetMapping("/entry-transaksi-siswa/all")
    public List<EntryTransaksiSiswa> getAllEntryTransaksiSiswa() {
        return entryTransaksiSiswaService.getAllEntryTransaksiSiswa();
    }

    @GetMapping("/entry-transaksi-siswa/filter-by-date")
        public List<EntryTransaksiSiswa> getEntryTransaksiSiswaByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
        return entryTransaksiSiswaService.getEntryTransaksiSiswaByDate(startDate, endDate);
    }

    @GetMapping("/entry-transaksi-siswa/laporan")
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
		String headervalue = "attachment; filename=LaporanTransaksiSiswa_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

        List<EntryTransaksiSiswa> entryTransaksiSiswaList = entryTransaksiSiswaService.getEntryTransaksiSiswaByDate(startDate, endDate);

        LaporanTransaksiSiswaPDF laporanTransaksiSiswaPDF = new LaporanTransaksiSiswaPDF();
        laporanTransaksiSiswaPDF.setEntryTransaksiSiswaList(entryTransaksiSiswaList);
        laporanTransaksiSiswaPDF.generateLaporanTransaksi(response, title);
    }
}
