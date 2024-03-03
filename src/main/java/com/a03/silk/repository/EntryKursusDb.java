package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.EntryKursus;

import java.util.Date;
import java.util.List;

@Repository
public interface EntryKursusDb extends JpaRepository<EntryKursus, Long> {
    List<EntryKursus> findByTanggalPembayaranBetween(Date startDate, Date endDate);
}
