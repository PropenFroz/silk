package com.a03.silk.dto.request;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
public class LaporanDataSiswaDTO{
    private String bulan;
    private int jumlahSiswaBaru;
    private int jumlahSiswaCuti;
    private int jumlahSiswaCutiMasukKembali;
    private int jumlahSiswaOff;
    private int jumlahTotalSiswaAktif;
}
