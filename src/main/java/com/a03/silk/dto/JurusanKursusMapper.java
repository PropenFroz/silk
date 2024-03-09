package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateJurusanKursusRequestDTO;
import com.a03.silk.model.JurusanKursus;

@Mapper(componentModel = "spring")
public interface JurusanKursusMapper {
    JurusanKursus toJurusanKursus (CreateJurusanKursusRequestDTO createJurusanKursusRequestDTO);
}
