package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateEntryPendaftaranSiswaRequestDTO;
import com.a03.silk.model.EntryPendaftaran;

@Mapper(componentModel = "spring")
public interface PendaftaranSiswaMapper {
    
    EntryPendaftaran toEntryPendaftaran(CreateEntryPendaftaranSiswaRequestDTO createEntryPendaftaranSiswaRequestDTO);

}
