package com.ocode.cbrf.service;

import com.ocode.cbrf.model.RestrictionList;

import java.util.Map;
import java.util.Optional;

public interface RestrictionListService {
    RestrictionList save(RestrictionList restrictionList);
    void update(Long id, Map<String,String> data);
    Optional<RestrictionList> getById(Long id);
}
