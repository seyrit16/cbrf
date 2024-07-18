package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.ParticipantInfo;
import com.ocode.cbrf.repository.ParticipantInfoRepository;
import com.ocode.cbrf.service.ParticipantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
