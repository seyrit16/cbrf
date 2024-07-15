package com.ocode.cbrf.dto.mapper;

import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.model.BICDirectoryEntry;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BICDirectoryEntryMapper {
    BICDirectoryEntry INSTANCE = Mappers.getMapper(BICDirectoryEntry.class);

    BICDirectoryEntryDto toDto(BICDirectoryEntry bicDirectoryEntry);
    BICDirectoryEntry toEntity(BICDirectoryEntryDto bicDirectoryEntryDto);
}
