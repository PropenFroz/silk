package com.a03.silk.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a03.silk.model.EntryBuku;
import com.a03.silk.service.ReportBukuService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LaporanBukuController {

    @Autowired
    private ReportBukuService reportBukuService;

    // @GetMapping("/laporan-buku/filter-by-date")
    // public List<EntryBuku> getEntryBukuByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
    //     Calendar calendar = Calendar.getInstance();
    //     calendar.setTime(endDate);
    //     calendar.add(Calendar.DATE, 1);
    //     endDate = calendar.getTime();
    //     return reportBukuService.getEntryBukuByDate(startDate, endDate);
    // }


}
