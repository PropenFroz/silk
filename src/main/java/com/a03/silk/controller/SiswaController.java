package com.a03.silk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.request.LaporanDataSiswaDTO;
import com.a03.silk.dto.request.UpdateStatusSiswaRequestDTO;
import com.a03.silk.dto.response.DashboardSiswaPerJurusanResponseDTO;
import com.a03.silk.model.Siswa;
import com.a03.silk.service.JurusanKursusService;
import com.a03.silk.service.SiswaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class SiswaController {

    @Autowired
    SiswaService siswaService;

    @Autowired
    JurusanKursusService jurusanKursusService;

    @GetMapping("/siswa/all")
    public List<Siswa> getAllSiswa() {
        return siswaService.getAllSiswa();
    }

    @GetMapping("siswa/{idSiswa}")
    public Siswa getSiswaById(@PathVariable("idSiswa") long idSiswa) {
        return siswaService.getSiswaById(idSiswa);
    }

    @GetMapping("/siswa/by-jurusan/{idJurusan}")
    public List<Siswa> getAllSiswaByJurusan(@PathVariable("idJurusan") long idJurusan) {
        return siswaService.getAllSiswaByJurusan(idJurusan);
    }
    
    @PutMapping("/siswa/update/{id}")
    public Siswa updateStatusSiswa(@RequestBody UpdateStatusSiswaRequestDTO updateStatusSiswaRequestDTO, @PathVariable("id") long idSiswa) {
        updateStatusSiswaRequestDTO.setIdSiswa(idSiswa);
        return siswaService.updateStatusSiswa(updateStatusSiswaRequestDTO);
    }

    @GetMapping("/siswa/filter-by-tahun-jurusan")
    public List<Siswa> getEntryTransaksiSiswaByDateJurusan(@RequestParam("tahun") int tahun, @RequestParam("idJurusan") long idJurusan) {
        return siswaService.getIuranSiswaByTahunJurusan(idJurusan, tahun);
    }

    @GetMapping("/siswa/dashboard/{idJurusan}")
    public DashboardSiswaPerJurusanResponseDTO dashboardSiswaPerJurusan(@PathVariable("idJurusan") long idJurusan) {
        DashboardSiswaPerJurusanResponseDTO dashboardSiswaPerJurusanResponseDTO = new DashboardSiswaPerJurusanResponseDTO();
        dashboardSiswaPerJurusanResponseDTO.setBaru(siswaService.countByStatusAndJurusanKursus(1, jurusanKursusService.getJurusanKursusById(idJurusan)));
        dashboardSiswaPerJurusanResponseDTO.setCuti(siswaService.countByStatusAndJurusanKursus(2, jurusanKursusService.getJurusanKursusById(idJurusan)));
        dashboardSiswaPerJurusanResponseDTO.setCutiMasukKembali(siswaService.countByStatusAndJurusanKursus(3, jurusanKursusService.getJurusanKursusById(idJurusan)));
        dashboardSiswaPerJurusanResponseDTO.setOff(siswaService.countByStatusAndJurusanKursus(4, jurusanKursusService.getJurusanKursusById(idJurusan)));
        return dashboardSiswaPerJurusanResponseDTO;
    }
    
    @GetMapping("/siswa/jumlah/{tahun}")
    public ResponseEntity<List<LaporanDataSiswaDTO>> getJumlahSiswaByTahun(@PathVariable int tahun) {
        List<LaporanDataSiswaDTO> laporanSiswa = siswaService.getDataJumlahSiswaByTahun(tahun);
        return ResponseEntity.ok(laporanSiswa);
    }
    
}