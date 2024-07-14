package com.ocode.cbrf.dto.mapper;

import com.ocode.cbrf.dto.impl.RestrictionListDto;
import com.ocode.cbrf.model.RestrictionList;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestrictionListMapper {
    RestrictionListMapper INSTANCE = Mappers.getMapper(RestrictionListMapper.class);

    RestrictionListDto toDto(RestrictionList restrictionList);
    RestrictionList toEntity(RestrictionListDto restrictionListDto);
}
