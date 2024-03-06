package com.a03.silk.dto.request;

import java.util.Date;

import com.a03.silk.model.BukuPurwacaraka;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryTransaksiBukuRequestDTO {
    private BukuPurwacaraka bukuPurwacaraka;
    private Date tanggalBeli;
    private Date tanggalJual;
    private int jumlahBeli;
    private int jumlahJual;
    private long hargaBeli;
    private long hargaJual;
}