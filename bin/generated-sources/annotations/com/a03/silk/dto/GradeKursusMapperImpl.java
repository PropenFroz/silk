package com.a03.silk.dto;

import com.a03.silk.dto.request.CreateGradeKursusRequestDTO;
import com.a03.silk.model.GradeKursus;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-06T23:22:31+0700",
    comments = "version: 1.5.0.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240206-1609, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class GradeKursusMapperImpl implements GradeKursusMapper {

    @Override
    public GradeKursus toGradeKursus(CreateGradeKursusRequestDTO createGradeKursusRequestDTO) {
        if ( createGradeKursusRequestDTO == null ) {
            return null;
        }

        GradeKursus gradeKursus = new GradeKursus();

        gradeKursus.setNamaGrade( createGradeKursusRequestDTO.getNamaGrade() );

        return gradeKursus;
    }
}
