package com.ocode.cbrf.service.mapper.impl;

import com.ocode.cbrf.dto.impl.ParticipantInfoDto;
import com.ocode.cbrf.dto.impl.RestrictionListDto;
import com.ocode.cbrf.model.ParticipantInfo;
import com.ocode.cbrf.model.RestrictionList;
import com.ocode.cbrf.service.mapper.AbstractCbrfMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantInfoMapperImpl extends AbstractCbrfMapper<ParticipantInfo, ParticipantInfoDto> {
    private final RestrictionListMapperImpl restrictionListMapper;
    public ParticipantInfoMapperImpl(RestrictionListMapperImpl restrictionListMapper) {
        super(ParticipantInfo.class, ParticipantInfoDto.class);
        this.restrictionListMapper = restrictionListMapper;
    }

    @Override
    public ParticipantInfoDto toDto(ParticipantInfo entity) {
        if (entity == null) {
            return null;
        }

        ParticipantInfoDto dto = super.toDto(entity);

        if (entity.getRestrictionLists() != null) {
            List<RestrictionListDto> restrictionListDtos = entity.getRestrictionLists()
                    .stream()
                    .map(restrictionListMapper::toDto)
                    .collect(Collectors.toList());
            dto.setRestrictionLists(restrictionListDtos);
        }

        return dto;
    }

    @Override
    public ParticipantInfo toEntity(ParticipantInfoDto dto) {
        if (dto == null) {
            return null;
        }

        ParticipantInfo entity = super.toEntity(dto);

        if (dto.getRestrictionLists() != null) {
            List<RestrictionList> restrictionLists = dto.getRestrictionLists()
                    .stream()
                    .map(restrictionListMapper::toEntity)
                    .collect(Collectors.toList());
            entity.setRestrictionLists(restrictionLists);
        }

        return entity;
    }
}
