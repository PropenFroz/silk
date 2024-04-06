package com.a03.silk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.request.CreateEntryGajiGuruRequestDTO;
import com.a03.silk.model.EntryGajiGuru;
import com.a03.silk.service.EntryGajiGuruService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class EntryGajiGuruController {

    @Autowired
    EntryGajiGuruService entryGajiGuruService;

    @PostMapping("/entry-gaji-guru")
    public EntryGajiGuru createEntryGajiGuru(@RequestBody CreateEntryGajiGuruRequestDTO createEntryGajiGuruRequestDTO) {        
        return entryGajiGuruService.createEntryGajiGuru(createEntryGajiGuruRequestDTO);
    }
    
    @GetMapping("/entry-gaji-guru/all")
    public List<EntryGajiGuru> getAllEntryGajiGuru() {
        return entryGajiGuruService.getAllEntryGajiGuru();
    }
    
}
