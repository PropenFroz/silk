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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.request.CreateEntryTransaksiSiswaRequestDTO;
import com.a03.silk.dto.request.UpdateEntryTransaksiSiswaRequestDTO;
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
    
    @PostMapping("/entry-transaksi-siswa")
    public EntryTransaksiSiswa createEntryKursusSiswa(@RequestBody CreateEntryTransaksiSiswaRequestDTO createEntryTransaksiSiswaRequestDTO) {
        return entryTransaksiSiswaService.createEntryTransaksiSiswa(createEntryTransaksiSiswaRequestDTO);
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
        laporanTransaksiSiswaPDF.generateLaporanTransaksiSiswa(response, title, entryTransaksiSiswaList);
    }

    @DeleteMapping("entry-transaksi-siswa/delete/{id}")
    public EntryTransaksiSiswa deleteEntryTransaksiSiswa(@PathVariable("id") long id) {
        var entryTransaksiSiswa = entryTransaksiSiswaService.getEntryTransaksiSiswaById(id);

        entryTransaksiSiswaService.deleteEntryTransaksiSiswa(entryTransaksiSiswa);

        return entryTransaksiSiswa;
    }

   

    @PutMapping("/entry-transaksi-siswa/update/{id}")
    public EntryTransaksiSiswa updateEntryTransaksiSiswa(@RequestBody UpdateEntryTransaksiSiswaRequestDTO entryTransaksiSiswaDTO, @PathVariable("id") long idEntryTransaksi){
        entryTransaksiSiswaDTO.setIdEntryTransaksiSiswa(idEntryTransaksi);
        return entryTransaksiSiswaService.updateEntry(entryTransaksiSiswaDTO);
    }
}
