package com.a03.silk.dto;

import com.a03.silk.dto.request.CreateBukuPurwacarakaRequestDTO;
import com.a03.silk.model.BukuPurwacaraka;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T23:22:32+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class BukuPurwacarakaMapperImpl implements BukuPurwacarakaMapper {

    @Override
    public BukuPurwacaraka toBukuPurwacaraka(CreateBukuPurwacarakaRequestDTO createBukuPurwacarakaRequestDTO) {
        if ( createBukuPurwacarakaRequestDTO == null ) {
            return null;
        }

        BukuPurwacaraka bukuPurwacaraka = new BukuPurwacaraka();

        bukuPurwacaraka.setJumlah( createBukuPurwacarakaRequestDTO.getJumlah() );
        bukuPurwacaraka.setJurusanKursus( createBukuPurwacarakaRequestDTO.getJurusanKursus() );
        bukuPurwacaraka.setNamaBuku( createBukuPurwacarakaRequestDTO.getNamaBuku() );

        return bukuPurwacaraka;
    }
}
