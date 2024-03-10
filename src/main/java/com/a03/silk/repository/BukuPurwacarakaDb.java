package com.a03.silk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a03.silk.model.BukuPurwacaraka;

@Repository
public interface BukuPurwacarakaDb extends JpaRepository<BukuPurwacaraka, Long> {
    BukuPurwacaraka findByIdBukuPurwacaraka(long idBukuPurwacaraka);
}
