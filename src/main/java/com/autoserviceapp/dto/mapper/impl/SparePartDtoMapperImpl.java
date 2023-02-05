package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.mapper.RequestDtoMapper;
import com.autoserviceapp.dto.mapper.ResponseDtoMapper;
import com.autoserviceapp.dto.request.SparePartRequestDto;
import com.autoserviceapp.dto.response.SparePartResponseDto;
import com.autoserviceapp.model.SparePart;
import org.springframework.stereotype.Component;

@Component
public class SparePartDtoMapperImpl implements RequestDtoMapper<SparePartRequestDto, SparePart>,
        ResponseDtoMapper<SparePartResponseDto, SparePart> {
    @Override
    public SparePart mapToModel(SparePartRequestDto dto) {
        SparePart sparePart = new SparePart();
        sparePart.setName(dto.getName());
        sparePart.setPrice(dto.getPrice());
        return sparePart;
    }

    @Override
    public SparePartResponseDto mapToDto(SparePart sparePart) {
        SparePartResponseDto responseDto = new SparePartResponseDto();
        responseDto.setId(sparePart.getId());
        responseDto.setName(sparePart.getName());
        responseDto.setPrice(sparePart.getPrice());
        return responseDto;
    }
}
