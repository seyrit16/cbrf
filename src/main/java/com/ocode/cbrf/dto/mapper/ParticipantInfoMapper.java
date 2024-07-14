package com.ocode.cbrf.dto.mapper;

import com.ocode.cbrf.dto.impl.ParticipantInfoDto;
import com.ocode.cbrf.model.ParticipantInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ParticipantInfoMapper {
    ParticipantInfo INSTANCE = Mappers.getMapper(ParticipantInfo.class);

    ParticipantInfoDto toDto(ParticipantInfo participantInfo);
    ParticipantInfo toEntity(ParticipantInfoDto participantInfoDto);
}
