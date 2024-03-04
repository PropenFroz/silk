package com.a03.silk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.EntryBuku;
import java.util.Date;


@Repository
public interface EntryTransaksiBukuDb extends JpaRepository<EntryBuku, Long> {
    
}
