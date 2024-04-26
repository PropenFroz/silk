package com.a03.silk.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSiswaPerJurusanResponseDTO {
    long baru;
    long cuti;
    long cutiMasukKembali;
    long off;
}
