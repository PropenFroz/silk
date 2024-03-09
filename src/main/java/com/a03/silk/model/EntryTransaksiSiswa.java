package com.a03.silk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entry_transaksi_siswa")
public class EntryTransaksiSiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEntryTransaksiSiswa;

    @NotNull
    @Column(name = "jenis_transaksi", nullable = false)
    private int jenisTransaksi;

    @NotNull
    @Column(name = "tanggal_pembayaran", nullable = false)
    private Date tanggalPembayaran;

    @NotNull
    @Column(name = "nama_siswa", nullable = false)
    private String namaSiswa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jurusan_kursus", referencedColumnName = "idJurusanKursus")
    private JurusanKursus jurusanKursus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grade_kursus", referencedColumnName = "idGradeKursus")
    private GradeKursus gradeKursus;

    @NotNull
    @Column(name = "uang_pendaftaran", nullable = false)
    private long uangPendaftaran;

    @NotNull
    @Column(name = "uang_kursus", nullable = false)
    private long uangKursus;

    @NotNull
    @Column(name = "uang_buku", nullable = false)
    private long uangBuku;

    @NotNull
    @Column(name = "cash", nullable = false)
    private long cash;

    @NotNull
    @Column(name = "transfer", nullable = false)
    private long transfer;

    @NotNull
    @Column(name = "keterangan", nullable = false)
    private String keterangan;  

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
