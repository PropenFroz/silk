package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.response.DashboardGrafikTotalPendapatanResponseDTO;
import com.a03.silk.dto.response.DashboardGrafikTotalPengeluaranResponseDTO;
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

    @GetMapping("/dashboard/grafik-pendapatan")
    public DashboardGrafikTotalPendapatanResponseDTO getGrafikPendapatanPerTahun(@RequestParam("tahun") int tahun) {
        DashboardGrafikTotalPendapatanResponseDTO response = new DashboardGrafikTotalPendapatanResponseDTO();
        long[] totalPendapatanPerBulan = dashboardService.getTotalPendapatanSetahun(tahun);
        response.setJanuari(totalPendapatanPerBulan[0]);
        response.setFebruari(totalPendapatanPerBulan[1]);
        response.setMaret(totalPendapatanPerBulan[2]);
        response.setApril(totalPendapatanPerBulan[3]);
        response.setMei(totalPendapatanPerBulan[4]);
        response.setJuni(totalPendapatanPerBulan[5]);
        response.setJuli(totalPendapatanPerBulan[6]);
        response.setAgustus(totalPendapatanPerBulan[7]);
        response.setSeptember(totalPendapatanPerBulan[8]);
        response.setOktober(totalPendapatanPerBulan[9]);
        response.setNovember(totalPendapatanPerBulan[10]);
        response.setDesember(totalPendapatanPerBulan[11]);
        return response;
    }

    @GetMapping("/dashboard/grafik-pengeluaran")
    public DashboardGrafikTotalPengeluaranResponseDTO getGrafikPengeluaranPerTahun(@RequestParam("tahun") int tahun) {
        DashboardGrafikTotalPengeluaranResponseDTO response = new DashboardGrafikTotalPengeluaranResponseDTO();
        long[] totalPendapatanPerBulan = dashboardService.getTotalPengeluaranSetahun(tahun);
        response.setJanuari(totalPendapatanPerBulan[0]);
        response.setFebruari(totalPendapatanPerBulan[1]);
        response.setMaret(totalPendapatanPerBulan[2]);
        response.setApril(totalPendapatanPerBulan[3]);
        response.setMei(totalPendapatanPerBulan[4]);
        response.setJuni(totalPendapatanPerBulan[5]);
        response.setJuli(totalPendapatanPerBulan[6]);
        response.setAgustus(totalPendapatanPerBulan[7]);
        response.setSeptember(totalPendapatanPerBulan[8]);
        response.setOktober(totalPendapatanPerBulan[9]);
        response.setNovember(totalPendapatanPerBulan[10]);
        response.setDesember(totalPendapatanPerBulan[11]);
        return response;
    }
        
    @GetMapping("/dashboard/pengeluaran")
    public long getPengeluaranPerTahun(@RequestParam("tahun") int tahun) {
        return dashboardService.getTotalPengeluaranByYear(tahun);
    }
}
