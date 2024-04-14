package com.a03.silk.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryTransaksiSiswaRequestDTO {
    private int jenisTransaksi;
    private Date tanggalPembayaran;
    private String namaSiswa;
    private long jurusanKursus;
    private long gradeKursus;
    private long uangPendaftaran;
    private long uangKursus;
    private long uangBuku;
    private long cash;
    private long transfer;
    private String keterangan;
}