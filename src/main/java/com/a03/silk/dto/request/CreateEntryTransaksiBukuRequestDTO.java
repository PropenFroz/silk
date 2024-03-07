package com.a03.silk.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryTransaksiBukuRequestDTO {
    private long idBukuPurwacaraka;
    private Date tanggalBeli;
    private Date tanggalJual;
    private int jumlahBeli;
    private int jumlahJual;
    private long hargaBeli;
    private long hargaJual;
}