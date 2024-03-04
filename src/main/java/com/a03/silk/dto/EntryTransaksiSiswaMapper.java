package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateEntryTransaksiSiswaRequestDTO;
import com.a03.silk.model.EntryTransaksiSiswa;

@Mapper(componentModel = "spring")
public interface EntryTransaksiSiswaMapper {
    EntryTransaksiSiswa toEntryTransaksiSiswa(CreateEntryTransaksiSiswaRequestDTO createEntryTransaksiSiswaRequestDTO);
}
