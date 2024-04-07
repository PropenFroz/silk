package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateGuruRequestDTO;
import com.a03.silk.model.Guru;

@Mapper(componentModel = "spring")
public interface GuruMapper {

    Guru toGuru (CreateGuruRequestDTO createGuruRequestDTO);
    
}
