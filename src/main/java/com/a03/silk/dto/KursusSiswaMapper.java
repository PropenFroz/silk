package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateEntryKursusSiswaRequestDTO;
import com.a03.silk.model.EntryKursus;

@Mapper(componentModel = "spring")
public interface KursusSiswaMapper {
    EntryKursus toEntryKursus(CreateEntryKursusSiswaRequestDTO createEntryKursusSiswaRequestDTO);
}
