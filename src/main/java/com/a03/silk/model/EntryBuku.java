package com.a03.silk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entry_buku")
public class EntryBuku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nama_buku")
    private String namaBuku;

    @NotNull
    @Column(name = "jurusan")
    private String jurusan;

    @NotNull
    @Column(name = "jumlah")
    private int jumlah;

    @Column(name = "tanggal_beli")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalBeli;

    @NotNull
    @Column(name = "jumlah_beli")
    private int jumlahBeli;

    @NotNull
    @Column(name = "harga_beli")
    private BigDecimal hargaBeli;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tanggal_jual")
    private Date tanggalJual;

    @NotNull
    @Column(name = "jumlah_jual")
    private int jumlahJual;

    @NotNull
    @Column(name = "sisa")
    private int sisa;

    @NotNull
    @Column(name = "harga_jual")
    private BigDecimal hargaJual;

    @Column(name = "keuntungan")
    private BigDecimal keuntungan;
    
    @Column(name = "total_keuntungan")
    private BigDecimal totalKeuntungan;
    
    @Column(name = "total_penjualan")
    private BigDecimal totalPenjualan;
}