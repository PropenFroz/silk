package com.a03.silk.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryLainnyaSiswaRequestDTO {

    private int jenisTransaksi;
    private Date tanggalPembayaran;
    private long siswa;
    private long uangPendaftaran;
    private long uangKursus;
    private long uangBuku;
    private long cash;
    private long transfer;
    private String keterangan;
    
}
