package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateGradeKursusRequestDTO;
import com.a03.silk.model.GradeKursus;

@Mapper(componentModel = "spring")
public interface GradeKursusMapper {
    GradeKursus toGradeKursus (CreateGradeKursusRequestDTO createGradeKursusRequestDTO);
}
