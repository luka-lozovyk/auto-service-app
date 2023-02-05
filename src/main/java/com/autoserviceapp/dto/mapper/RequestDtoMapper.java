package com.autoserviceapp.dto.mapper;

public interface RequestDtoMapper<D, M> {
    M mapToModel(D dto);
}
