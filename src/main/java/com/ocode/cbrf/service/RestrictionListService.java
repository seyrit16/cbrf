package com.ocode.cbrf.service;

import com.ocode.cbrf.model.RestrictionList;

public interface RestrictionListService {
    RestrictionList save(RestrictionList restrictionList);
    void delete(Long id);
}
