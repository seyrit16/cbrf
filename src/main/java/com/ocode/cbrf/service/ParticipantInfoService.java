package com.ocode.cbrf.service;

import com.ocode.cbrf.model.ParticipantInfo;

import java.util.Map;
import java.util.Optional;

public interface ParticipantInfoService {
    ParticipantInfo save(ParticipantInfo participantInfo);
    void delete(Long id);
    void update(Long id, Map<String,String> data);
    Optional<ParticipantInfo> getById(Long id);
}
