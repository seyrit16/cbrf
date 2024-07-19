package com.ocode.cbrf.service;

import com.ocode.cbrf.dto.impl.ED807Dto;
import com.ocode.cbrf.model.ED807;

public interface DtoService {
    ED807 toEntities(ED807Dto ed807Dto);
}
