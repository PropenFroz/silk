package com.a03.silk.dto.request;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryTransaksiBukuRequestDTO {
    private String namaBuku;
    private String jurusan;
    private int jumlah;
    private Date tanggalBeli;
    private Date tanggalJual;
    private int jumlahBeli;
    private int jumlahJual;
    private int sisa;
    private BigDecimal hargaBeli;
    private BigDecimal hargaJual;
}
