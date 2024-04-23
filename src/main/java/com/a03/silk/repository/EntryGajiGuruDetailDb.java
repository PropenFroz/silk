package com.a03.silk.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.EntryGajiGuruDetail;
import com.a03.silk.model.Guru;

@Repository
public interface EntryGajiGuruDetailDb extends JpaRepository<EntryGajiGuruDetail, Long> {
    List<EntryGajiGuruDetail> findByEntryGajiGuru_GuruAndTanggalBetween(Guru guru, Date startDate, Date endDate);
    List<EntryGajiGuruDetail> findByTanggalBetweenOrderByTanggalAsc(Date startDate, Date endDate);
}
