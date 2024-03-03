package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.EntryLainnya;

import java.util.Date;
import java.util.List;

@Repository
public interface EntryLainnyaDb extends JpaRepository<EntryLainnya, Long> {
    List<EntryLainnya> findByTanggalPembayaranBetween(Date startDate, Date endDate);
}
