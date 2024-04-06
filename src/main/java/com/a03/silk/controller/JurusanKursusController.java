package com.a03.silk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.JurusanKursusMapper;
import com.a03.silk.dto.request.CreateJurusanKursusRequestDTO;
import com.a03.silk.model.JurusanKursus;
import com.a03.silk.service.JurusanKursusService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class JurusanKursusController {
    
    @Autowired
    JurusanKursusService jurusanKursusService;

    @Autowired
    JurusanKursusMapper jurusanKursusMapper;

    @GetMapping("/jurusan-kursus/all")
    public List<JurusanKursus> getAllJurusanKursus() {
        return jurusanKursusService.getAllJurusanKursus();
    }

    @PostMapping("/jurusan-kursus")
    public JurusanKursus createJurusanKursus(@RequestBody CreateJurusanKursusRequestDTO createJurusanKursusRequestDTO) {
        var jurusanKursus = jurusanKursusMapper.toJurusanKursus(createJurusanKursusRequestDTO);
        return jurusanKursusService.createJurusanKursus(jurusanKursus);
    }
}
