package com.a03.silk.dto;

import com.a03.silk.dto.request.CreateJurusanKursusRequestDTO;
import com.a03.silk.model.JurusanKursus;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T23:22:32+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class JurusanKursusMapperImpl implements JurusanKursusMapper {

    @Override
    public JurusanKursus toJurusanKursus(CreateJurusanKursusRequestDTO createJurusanKursusRequestDTO) {
        if ( createJurusanKursusRequestDTO == null ) {
            return null;
        }

        JurusanKursus jurusanKursus = new JurusanKursus();

        jurusanKursus.setNamaJurusan( createJurusanKursusRequestDTO.getNamaJurusan() );

        return jurusanKursus;
    }
}
