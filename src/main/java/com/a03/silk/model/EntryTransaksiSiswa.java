package com.a03.silk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @NotNull
    @Column(name = "jurusan", nullable = false)
    private String jurusan;

    @NotNull
    @Column(name = "grade", nullable = false)
    private String grade;

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
}
