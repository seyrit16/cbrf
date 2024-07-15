package com.ocode.cbrf.dto.mapper;

import com.ocode.cbrf.dto.impl.SWBICSDto;
import com.ocode.cbrf.model.SWBICS;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SWBICSMapper {
    SWBICSMapper INSTANCE = Mappers.getMapper(SWBICSMapper.class);

    SWBICSDto toDto(SWBICS swbics);
    SWBICS toEntity(SWBICSDto swbicsDto);
}
