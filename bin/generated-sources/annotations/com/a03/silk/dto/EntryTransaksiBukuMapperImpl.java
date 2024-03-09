package com.a03.silk.dto;

import com.a03.silk.dto.request.CreateEntryTransaksiBukuRequestDTO;
import com.a03.silk.model.EntryTransaksiBuku;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T23:22:32+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class EntryTransaksiBukuMapperImpl implements EntryTransaksiBukuMapper {

    @Override
    public EntryTransaksiBuku toEntryBuku(CreateEntryTransaksiBukuRequestDTO createEntryTransaksiBukuRequestDTO) {
        if ( createEntryTransaksiBukuRequestDTO == null ) {
            return null;
        }

        EntryTransaksiBuku entryTransaksiBuku = new EntryTransaksiBuku();

        entryTransaksiBuku.setBukuPurwacaraka( createEntryTransaksiBukuRequestDTO.getBukuPurwacaraka() );
        entryTransaksiBuku.setHargaBeli( createEntryTransaksiBukuRequestDTO.getHargaBeli() );
        entryTransaksiBuku.setHargaJual( createEntryTransaksiBukuRequestDTO.getHargaJual() );
        entryTransaksiBuku.setJumlahBeli( createEntryTransaksiBukuRequestDTO.getJumlahBeli() );
        entryTransaksiBuku.setJumlahJual( createEntryTransaksiBukuRequestDTO.getJumlahJual() );
        entryTransaksiBuku.setTanggalBeli( createEntryTransaksiBukuRequestDTO.getTanggalBeli() );
        entryTransaksiBuku.setTanggalJual( createEntryTransaksiBukuRequestDTO.getTanggalJual() );

        setJumlahAndSiswa( createEntryTransaksiBukuRequestDTO, entryTransaksiBuku );

        return entryTransaksiBuku;
    }
}
