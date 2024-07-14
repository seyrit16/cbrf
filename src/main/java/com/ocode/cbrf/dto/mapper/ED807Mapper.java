package com.ocode.cbrf.dto.mapper;

import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.model.ED807;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ED807Mapper {
    ED807Mapper INSTANCE = Mappers.getMapper(ED807Mapper.class);

    ED807Dto toDto(ED807 ed807);
    ED807 toEntity(ED807Dto ed807Dto);
}
