package com.a03.silk.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
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
@Table(name = "siswa")
public class Siswa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSiswa;

    @NotNull
    @Column(name = "nama_siswa")
    private String namaSiswa;

    @NotNull
    @Column(name = "status")
    private int status = 1;

    @NotNull
    @Column(name = "tanggal_daftar")
    private Date tanggalDaftar;

    @NotNull
    @Column(name = "id_pendaftaran")
    private long idPendaftaran;

    @ElementCollection
    @CollectionTable(name = "tanggal_kursus", joinColumns = @JoinColumn(name = "siswa_id"))
    @MapKeyColumn(name = "tahun")
    @Column(name = "tanggal")
    private Map<Integer, List<Long>> tanggalKursusPerTahun = new HashMap<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jurusan_kursus", referencedColumnName = "idJurusanKursus")
    private JurusanKursus jurusanKursus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grade_kursus", referencedColumnName = "idGradeKursus")
    private GradeKursus gradeKursus;

    @JsonIgnore
    @OneToMany(mappedBy = "siswa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EntryTransaksiSiswa> daftarTransaksiSiswa;

    @JsonIgnore
    @OneToMany(mappedBy = "siswa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EntryGajiGuruDetail> daftarGajiGuruDetail;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;
}

