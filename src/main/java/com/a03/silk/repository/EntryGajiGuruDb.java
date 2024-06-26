package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.EntryGajiGuru;

import java.util.List;

@Repository
public interface EntryGajiGuruDb extends JpaRepository<EntryGajiGuru, Long> {

}

