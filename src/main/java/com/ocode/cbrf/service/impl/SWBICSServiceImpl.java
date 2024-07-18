package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.SWBICS;
import com.ocode.cbrf.repository.SWBICSRepository;
import com.ocode.cbrf.service.SWBICSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SWBICSServiceImpl implements SWBICSService {
    @Autowired
    SWBICSRepository swbicsRepository;

    @Override
    public SWBICS save(SWBICS swbics) {
        return swbicsRepository.save(swbics);
    }

    @Override
    public void delete(Long id) {

    }
}
