package com.a03.silk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.model.Siswa;
import com.a03.silk.service.SiswaService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class SiswaController {

    @Autowired
    SiswaService siswaService;

    @GetMapping("/siswa/all")
    public List<Siswa> getAllGuru() {
        return siswaService.getAllSiswa();
    }

    @GetMapping("siswa/{idSiswa}")
    public Siswa getGuruById(@PathVariable("idSiswa") long idSiswa) {
        return siswaService.getSiswaById(idSiswa);
    }

    @GetMapping("/siswa/filter-by-tahun-jurusan")
    public List<Siswa> getEntryTransaksiSiswaByDateJurusan(@RequestParam("tahun") int tahun, @RequestParam("idJurusan") long idJurusan) {
        return siswaService.getIuranSiswaByTahunJurusan(idJurusan, tahun);
    }
}