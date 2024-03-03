package com.a03.silk.dto.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryKursusSiswaRequestDTO {
    private Date tanggalPembayaran;
    private String namaSiswa;
    private String jurusan;
    private String grade;
    private long uangPendaftaran;
    private long uangKursus;
    private long uangBuku;
    private long cash;
    private long transfer;
    private String keterangan;
}