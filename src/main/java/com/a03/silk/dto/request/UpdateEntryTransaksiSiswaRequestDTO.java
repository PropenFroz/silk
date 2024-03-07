package com.a03.silk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateEntryTransaksiSiswaRequestDTO extends CreateEntryTransaksiSiswaRequestDTO {
    private long idEntryTransaksiSiswa;
}