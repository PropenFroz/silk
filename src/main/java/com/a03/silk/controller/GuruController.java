package com.a03.silk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.GuruMapper;
import com.a03.silk.dto.request.CreateGuruRequestDTO;
import com.a03.silk.model.Guru;
import com.a03.silk.service.GuruService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class GuruController {

    @Autowired
    GuruService guruService;

    @Autowired
    GuruMapper guruMapper;

    @PostMapping("/guru")
    public Guru createGuru(@RequestBody CreateGuruRequestDTO createGuruRequestDTO) {
        var guru = guruMapper.toGuru(createGuruRequestDTO);
        return guruService.createGuru(guru);
    }

    @GetMapping("/guru/all")
    public List<Guru> getAllGuru() {
        return guruService.getAllGuru();
    }
    
}
