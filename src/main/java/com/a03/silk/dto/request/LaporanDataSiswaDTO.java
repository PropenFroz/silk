package com.a03.silk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
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
