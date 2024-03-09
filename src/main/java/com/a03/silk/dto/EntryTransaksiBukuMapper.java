package com.a03.silk.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.a03.silk.dto.request.CreateEntryTransaksiBukuRequestDTO;
import com.a03.silk.model.EntryTransaksiBuku;

@Mapper(componentModel = "spring")
public interface EntryTransaksiBukuMapper {

    // EntryTransaksiBuku toEntryBuku(CreateEntryTransaksiBukuRequestDTO createEntryTransaksiBukuRequestDTO);
    // @AfterMapping
    // default void setJumlahAndSiswa(CreateEntryTransaksiBukuRequestDTO source, @MappingTarget EntryTransaksiBuku target) {
    //     target.setJumlah(source.getBukuPurwacaraka().getJumlah());
    // }
}
