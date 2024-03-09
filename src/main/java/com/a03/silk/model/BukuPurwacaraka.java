package com.a03.silk.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buku_purwacaraka")
public class BukuPurwacaraka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBukuPurwacaraka;

    @NotNull
    @Column(name = "nama_buku")
    private String namaBuku;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jurusan_kursus", referencedColumnName = "idJurusanKursus")
    private JurusanKursus jurusanKursus;

    @NotNull
    @Column(name = "jumlah")
    private int jumlah;

    @JsonIgnore
    @OneToMany(mappedBy = "bukuPurwacaraka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EntryTransaksiBuku> daftarEntryBuku;
}
