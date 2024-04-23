package com.a03.silk.dto.request;

import com.a03.silk.model.JurusanKursus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBukuPurwacarakaRequestDTO {

    private String namaBuku;
    private long jurusanKursus;
    private int jumlah;

}
