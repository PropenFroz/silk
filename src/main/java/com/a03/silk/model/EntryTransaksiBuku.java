package com.a03.silk.model;

import jakarta.persistence.*;
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
@Table(name = "entry_transaksi_buku")
public class EntryTransaksiBuku { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntryBuku;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_buku_purwacaraka", referencedColumnName = "idBukuPurwacaraka")
    private BukuPurwacaraka bukuPurwacaraka;

    @Column(name = "jumlah_stok")
    private long jumlah;

    @Column(name = "tanggal_beli")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalBeli;

    @NotNull
    @Column(name = "jumlah_beli")
    private int jumlahBeli;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tanggal_jual")
    private Date tanggalJual;

    @NotNull
    @Column(name = "jumlah_jual")
    private int jumlahJual;

    @NotNull
    @Column(name = "harga_beli")
    private long hargaBeli;

    @NotNull
    @Column(name = "harga_jual")
    private long hargaJual;
}