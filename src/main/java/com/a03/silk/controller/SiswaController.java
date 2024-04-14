package com.a03.silk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.request.UpdateStatusSiswaRequestDTO;
import com.a03.silk.model.Siswa;
import com.a03.silk.service.SiswaService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class SiswaController {

    @Autowired
    SiswaService siswaService;

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
    
}
