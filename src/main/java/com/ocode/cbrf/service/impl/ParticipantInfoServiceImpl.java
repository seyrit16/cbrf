package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.invariants.ParticipantStatus;
import com.ocode.cbrf.model.ParticipantInfo;
import com.ocode.cbrf.repository.ParticipantInfoRepository;
import com.ocode.cbrf.service.ParticipantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class ParticipantInfoServiceImpl implements ParticipantInfoService {
    @Autowired
    ParticipantInfoRepository participantInfoRepository;

    @Override
    public ParticipantInfo save(ParticipantInfo participantInfo) {
        return participantInfoRepository.save(participantInfo);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Long id, Map<String, String> data) {
        ParticipantInfo participantInfo = getById(id).orElse(null);
        if(participantInfo == null)
            throw new NullPointerException("participant info is null");

        Optional.ofNullable(data.get("name")).ifPresent(participantInfo::setName);
        Optional.ofNullable(data.get("englishName")).ifPresent(participantInfo::setEnglishName);
        Optional.ofNullable(data.get("regNumber")).ifPresent(participantInfo::setRegNumber);
        Optional.ofNullable(data.get("countryCode")).ifPresent(participantInfo::setCountryCode);
        Optional.ofNullable(data.get("region")).ifPresent(participantInfo::setRegion);
        Optional.ofNullable(data.get("index")).map(Integer::parseInt).ifPresent(participantInfo::setIndex);
        Optional.ofNullable(data.get("typeNP")).ifPresent(participantInfo::setTypeNP);
        Optional.ofNullable(data.get("nameNP")).ifPresent(participantInfo::setNameNP);
        Optional.ofNullable(data.get("address")).ifPresent(participantInfo::setAddress);
        Optional.ofNullable(data.get("parentBIC")).map(Integer::parseInt).ifPresent(participantInfo::setParentBIC);
        Optional.ofNullable(data.get("dateIn"))
                .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .ifPresent(participantInfo::setDateIn);
        Optional.ofNullable(data.get("dateOut"))
                .map(dateStr -> LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .ifPresent(participantInfo::setDateOut);
        Optional.ofNullable(data.get("participantType")).ifPresent(participantInfo::setParticipantType);
        Optional.ofNullable(data.get("services")).ifPresent(participantInfo::setServices);
        Optional.ofNullable(data.get("exchangeType")).ifPresent(participantInfo::setExchangeType);
        Optional.ofNullable(data.get("UId")).map(Long::parseLong).ifPresent(participantInfo::setUId);
        Optional.ofNullable(data.get("participantStatus")).map(ParticipantStatus::valueOf).ifPresent(participantInfo::setParticipantStatus);

        participantInfoRepository.save(participantInfo);
    }

    @Override
    public Optional<ParticipantInfo> getById(Long id) {
        return participantInfoRepository.findById(id);
    }
}
