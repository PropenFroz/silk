package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateEntryTransaksiBukuRequestDTO;
import com.a03.silk.model.EntryBuku;

@Mapper(componentModel = "spring")
public interface TransaksiBukuMapper {
    EntryBuku toEntryBuku(CreateEntryTransaksiBukuRequestDTO createEntryTransaksiBukuRequestDTO);
}
