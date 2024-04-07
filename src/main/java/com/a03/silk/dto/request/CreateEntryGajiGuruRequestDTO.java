package com.a03.silk.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEntryGajiGuruRequestDTO {
    private long idGuru;
    private long idJurusanKursus;
    private List<CreateEntryGajiGuruDetailRequestDTO> listCreateEntryGajiGuruDetailRequestDTO;
}
