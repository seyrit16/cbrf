package com.ocode.cbrf.service;

import com.ocode.cbrf.model.SWBICS;

public interface SWBICSService {
    SWBICS save(SWBICS swbics);
    void delete(Long id);
}
