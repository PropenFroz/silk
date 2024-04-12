package com.a03.silk.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateEntryKursusSiswaRequestDTO {
    private long idEntryTransaksiSiswa;
    private int tahunKursus;
    private int bulanKursus;
    private Date tanggalPembayaran;
    private long siswa;
    private long uangPendaftaran;
    private long uangKursus;
    private long uangBuku;
    private long cash;
    private long transfer;
    private String keterangan;
}
