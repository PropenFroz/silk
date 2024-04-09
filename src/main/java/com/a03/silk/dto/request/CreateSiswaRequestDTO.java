package com.a03.silk.dto.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateSiswaRequestDTO {
    private String namaSiswa;
    private Date tanggalDaftar;
    private List<Date> tanggalKursus;
    private long idJurusanKursus;
}
