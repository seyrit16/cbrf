package com.ocode.cbrf.service;

import com.ocode.cbrf.model.ParticipantInfo;

public interface ParticipantInfoService {
    ParticipantInfo save(ParticipantInfo participantInfo);
    void delete(Long id);
}
