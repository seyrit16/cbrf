package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.model.RestrictionList;
import com.ocode.cbrf.repository.RestrictionListRepository;
import com.ocode.cbrf.service.RestrictionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestrictionListServiceImpl implements RestrictionListService {
    @Autowired
    RestrictionListRepository restrictionListRepository;

    @Override
    public RestrictionList save(RestrictionList restrictionList) {
        return restrictionListRepository.save(restrictionList);
    }

    @Override
    public void delete(Long id) {

    }
}
