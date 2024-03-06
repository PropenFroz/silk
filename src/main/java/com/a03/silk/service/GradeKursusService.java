package com.a03.silk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a03.silk.model.GradeKursus;
import com.a03.silk.repository.GradeKursusDb;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeKursusService {
    
    @Autowired
    GradeKursusDb gradeKursusDb;

    public GradeKursus createGradeKursus(GradeKursus gradeKursus) {
        return gradeKursusDb.save(gradeKursus);
    }

    public GradeKursus getGradeKursusById(long idGradeKursus) {
        return gradeKursusDb.findById(idGradeKursus).get();
    }

    public List<GradeKursus> getAllGradeKursus() {
        return gradeKursusDb.findAll();
    }
}
