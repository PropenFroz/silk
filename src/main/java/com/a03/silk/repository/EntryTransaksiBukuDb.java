package com.a03.silk.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.EntryTransaksiBuku;

@Repository
public interface EntryTransaksiBukuDb extends JpaRepository<EntryTransaksiBuku, Long> {

    @Query("SELECT etb FROM EntryTransaksiBuku etb " +
            "WHERE etb.tanggalBeli BETWEEN :startDate AND :endDate " +
            "AND etb.tanggalJual BETWEEN :startDate AND :endDate")
    List<EntryTransaksiBuku> findByTanggalBeliAndTanggalJualBetween(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

}