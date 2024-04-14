package com.a03.silk.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE iuran_siswa SET is_deleted = true WHERE id_iuran_siswa=?")
@Where(clause = "is_deleted=false")
@Table(name = "iuran_siswa")
public class IuranSiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idIuranSiswa;

    @NotNull
    @Column(name = "tahun", nullable = false)
    private int tahun;

    @NotNull
    @Column(name = "bulan", nullable = false)
    private int bulan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_siswa", referencedColumnName = "idSiswa")
    private Siswa siswa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_entry_transaksi_siswa", referencedColumnName = "idEntryTransaksiSiswa")
    private EntryTransaksiSiswa entryKursus;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;
}