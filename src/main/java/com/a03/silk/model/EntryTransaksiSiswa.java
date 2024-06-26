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

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_siswa", referencedColumnName = "idSiswa")
    private Siswa siswa;

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

    @JsonIgnore
    @OneToMany(mappedBy = "entryKursus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IuranSiswa> daftarIuranSiswa;
}
