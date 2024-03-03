package com.a03.silk.dto;

import org.mapstruct.Mapper;

import com.a03.silk.dto.request.CreateEntryLainnyaSiswaRequestDTO;
import com.a03.silk.model.EntryLainnya;

@Mapper(componentModel = "spring")
public interface LainnyaSiswaMapper {
    EntryLainnya toEntryLainnya (CreateEntryLainnyaSiswaRequestDTO createEntryLainnyaSiswaRequestDTO);
}
