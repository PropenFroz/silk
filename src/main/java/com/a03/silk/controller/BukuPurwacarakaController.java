package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.BukuPurwacarakaMapper;
import com.a03.silk.dto.request.CreateBukuPurwacarakaRequestDTO;
import com.a03.silk.model.BukuPurwacaraka;
import com.a03.silk.service.BukuPurwacarakaService;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class BukuPurwacarakaController {

    @Autowired
    BukuPurwacarakaService bukuPurwacarakaService;

    @Autowired
    BukuPurwacarakaMapper bukuPurwacarakaMapper;

    @PostMapping("/buku-purwacaraka") 
    public BukuPurwacaraka createBukuPurwacaraka(@RequestBody CreateBukuPurwacarakaRequestDTO createBukuPurwacarakaRequestDTO) {
        var bukuPurwacaraka =  bukuPurwacarakaMapper.toBukuPurwacaraka(createBukuPurwacarakaRequestDTO);
        return bukuPurwacarakaService.createBukuPurwacaraka(bukuPurwacaraka);
    }

    @GetMapping("/buku-purwacaraka/all")
    public List<BukuPurwacaraka> getAllBukuPurwacaraka() {
        return bukuPurwacarakaService.getAllBukuPurwacaraka();
    }

}
