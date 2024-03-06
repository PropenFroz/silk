package com.a03.silk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a03.silk.dto.GradeKursusMapper;
import com.a03.silk.dto.request.CreateGradeKursusRequestDTO;
import com.a03.silk.model.GradeKursus;
import com.a03.silk.service.GradeKursusService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class GradeKursusController {
    
    @Autowired
    GradeKursusService gradeKursusService;

    @Autowired
    GradeKursusMapper gradeKursusMapper;

    @PostMapping("/grade-kursus")
    public GradeKursus createGradeKursus(@RequestBody CreateGradeKursusRequestDTO createGradeKursusRequestDTO) {
        var gradeKursus =  gradeKursusMapper.toGradeKursus(createGradeKursusRequestDTO);
        return gradeKursusService.createGradeKursus(gradeKursus);
    }

    @GetMapping("/grade-kursus/all")
    public List<GradeKursus> getAllGradeKursus() {
        return gradeKursusService.getAllGradeKursus();
    }

    @GetMapping("/grade-kursus/{idGradeKursus}")
    public GradeKursus getGradeKursusById(@PathVariable long idGradeKursus) {
        return gradeKursusService.getGradeKursusById(idGradeKursus);
    }
}
