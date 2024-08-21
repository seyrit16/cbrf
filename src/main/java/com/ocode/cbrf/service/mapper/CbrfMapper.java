package com.ocode.cbrf.service.mapper;

public interface CbrfMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
