package com.a03.silk.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadEntryTransaksiBukuResponseDTO {
    private long bukuPurwacaraka;
    private Date tanggalBeli;
    private Date tanggalJual;
    private int jumlahBeli;
    private int jumlahJual;
    private long hargaBeli;
    private long hargaJual;
}
