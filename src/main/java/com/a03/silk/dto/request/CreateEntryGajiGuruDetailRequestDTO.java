package com.a03.silk.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryGajiGuruDetailRequestDTO {
    private String murid;
    private long idGradeKursus;
    private long uangKursus;
    private Date tanggal;
    private long minggu1;
    private long minggu2;
    private long minggu3;
    private long minggu4;
    private long feeGuru;
    private String keterangan;
}
