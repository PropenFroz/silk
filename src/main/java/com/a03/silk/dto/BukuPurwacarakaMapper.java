package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateBukuPurwacarakaRequestDTO;
import com.a03.silk.model.BukuPurwacaraka;

@Mapper(componentModel = "spring")
public interface BukuPurwacarakaMapper {
    BukuPurwacaraka toBukuPurwacaraka (CreateBukuPurwacarakaRequestDTO createBukuPurwacarakaRequestDTO);
}
