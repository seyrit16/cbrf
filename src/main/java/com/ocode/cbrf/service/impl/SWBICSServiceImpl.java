package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.SWBICS;
import com.ocode.cbrf.repository.SWBICSRepository;
import com.ocode.cbrf.service.SWBICSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SWBICSServiceImpl implements SWBICSService {
    @Autowired
    SWBICSRepository swbicsRepository;

    @Override
    public SWBICS save(SWBICS swbics) {
        Optional<SWBICS> optionalSwbicsBySwbic = swbicsRepository.findBySwbic(swbics.getSwbic());
        if(optionalSwbicsBySwbic.isEmpty())
            return swbicsRepository.save(swbics);
        else {
            swbics.setId(optionalSwbicsBySwbic.get().getId());
            return swbicsRepository.save(swbics);
        }
    }

    @Override
    public void delete(Long id) {

    }
}
