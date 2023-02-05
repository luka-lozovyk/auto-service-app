package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.mapper.RequestDtoMapper;
import com.autoserviceapp.dto.mapper.ResponseDtoMapper;
import com.autoserviceapp.dto.request.CarRequestDto;
import com.autoserviceapp.dto.response.CarResponseDto;
import com.autoserviceapp.model.Car;
import com.autoserviceapp.service.OwnerService;
import org.springframework.stereotype.Component;

@Component
public class CarDtoMapperImpl implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    private final OwnerService ownerService;

    public CarDtoMapperImpl(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setManufacturer(dto.getManufacturer());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setRegistrationNumber(dto.getRegistrationNumber());
        car.setOwner(ownerService.getOwnerById(dto.getOwnerId()));
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto responseDto = new CarResponseDto();
        responseDto.setId(car.getId());
        responseDto.setManufacturer(car.getManufacturer());
        responseDto.setModel(car.getModel());
        responseDto.setYear(car.getYear());
        responseDto.setRegistrationNumber(car.getRegistrationNumber());
        responseDto.setOwnerId(car.getOwner().getId());
        return responseDto;
    }
}
