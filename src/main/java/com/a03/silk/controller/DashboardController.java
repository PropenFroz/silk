package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.service.DashboardService;

@RestController
@CrossOrigin(origins = "https://silk-client.railway.internal")
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/dashboard/pendapatan")
    public long getPendapatanPerTahun(@RequestParam("tahun") int tahun) {
        return dashboardService.getTotalPendapatanBukuByYear(tahun) + dashboardService.getTotalPendapatanSiswaByYear(tahun);
    }
}
