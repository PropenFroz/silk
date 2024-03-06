package com.a03.silk.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jurusan_kursus")
public class JurusanKursus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJurusanKursus;

    @NotNull
    @Column(name = "nama_jurusan")
    private String namaJurusan;
    
    @JsonIgnore
    @OneToMany(mappedBy = "jurusanKursus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EntryTransaksiSiswa> daftarTransaksiSiswa;

    @JsonIgnore
    @OneToMany(mappedBy = "jurusanKursus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BukuPurwacaraka> daftarBukuPurwacaraka;
}
