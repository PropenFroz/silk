package com.a03.silk.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.model.EntryKursus;
import com.a03.silk.model.EntryLainnya;
import com.a03.silk.model.EntryPendaftaran;
import com.a03.silk.service.KursusSiswaService;
import com.a03.silk.service.LainnyaSiswaService;
import com.a03.silk.service.LaporanTransaksiSiswaPDF;
import com.a03.silk.service.PendaftaranSiswaService;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LaporanTransaksiSiswaController {

    @Autowired
    PendaftaranSiswaService pendaftaranSiswaService;

    @Autowired
    KursusSiswaService kursusSiswaService;

    @Autowired
    LainnyaSiswaService lainnyaSiswaService;

    @GetMapping("/laporan-transaksi-siswa/pdf")
    public void generateLaporanTransaksi(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, 
            HttpServletResponse response) throws DocumentException, IOException {
        
        response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

        List<EntryPendaftaran> entryPendaftaranList = pendaftaranSiswaService.getEntryPendaftaranSiswaByDate(startDate, endDate);
        List<EntryKursus> entryKursusList = kursusSiswaService.getEntryKursusSiswaByDate(startDate, endDate);
        List<EntryLainnya> entryLainnyaList = lainnyaSiswaService.getEntryLainnyaSiswaByDate(startDate, endDate);
        

        LaporanTransaksiSiswaPDF laporanTransaksiSiswaPDF = new LaporanTransaksiSiswaPDF();
        laporanTransaksiSiswaPDF.setEntryKursusList(entryKursusList);
        laporanTransaksiSiswaPDF.setEntryLainnyaList(entryLainnyaList);
        laporanTransaksiSiswaPDF.setEntryPendaftaranList(entryPendaftaranList);
        laporanTransaksiSiswaPDF.generateLaporanTransaksi(response, "2024");
    }
}
