package com.ocode.cbrf.service.mapper.impl;

import com.ocode.cbrf.dto.impl.BICDirectoryEntryDto;
import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.model.BICDirectoryEntry;
import com.ocode.cbrf.model.ED807;
import com.ocode.cbrf.service.mapper.AbstractCbrfMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ED807MapperImpl extends AbstractCbrfMapper<ED807, ED807Dto> {
    private final BICDirectoryEntryMapperImpl bicDirectoryEntryMapper;

    public ED807MapperImpl(BICDirectoryEntryMapperImpl bicDirectoryEntryMapper) {
        super(ED807.class, ED807Dto.class);
        this.bicDirectoryEntryMapper = bicDirectoryEntryMapper;
    }

    @Override
    public ED807Dto toDto(ED807 entity) {
        if (entity == null) {
            return null;
        }

        ED807Dto dto = super.toDto(entity);

        if (entity.getBicDirectoryEntries() != null) {
            List<BICDirectoryEntryDto> bicDirectoryEntryDtos = entity.getBicDirectoryEntries()
                    .stream()
                    .map(bicDirectoryEntryMapper::toDto)
                    .collect(Collectors.toList());
            dto.setBicDirectoryEntries(bicDirectoryEntryDtos);
        }

        return dto;
    }

    @Override
    public ED807 toEntity(ED807Dto dto) {
        if (dto == null) {
            return null;
        }

        ED807 entity = super.toEntity(dto);

        if (dto.getBicDirectoryEntries() != null) {
            List<BICDirectoryEntry> bicDirectoryEntryLists = dto.getBicDirectoryEntries()
                    .stream()
                    .map(bicDirectoryEntryMapper::toEntity)
                    .collect(Collectors.toList());
            entity.setBicDirectoryEntries(bicDirectoryEntryLists);
        }

        return entity;
    }
}