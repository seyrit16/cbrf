package com.ocode.cbrf.service.mapper;

import org.springframework.beans.BeanUtils;

public abstract class AbstractCbrfMapper<E, D> implements CbrfMapper<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    protected AbstractCbrfMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public D toDto(E entity) {
        if (entity == null) {
            return null;
        }
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Mapping error", e);
        }
    }

    @Override
    public E toEntity(D dto) {
        if (dto == null) {
            return null;
        }
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Mapping error", e);
        }
    }
}