package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.mapper.RequestDtoMapper;
import com.autoserviceapp.dto.mapper.ResponseDtoMapper;
import com.autoserviceapp.dto.request.OwnerRequestDto;
import com.autoserviceapp.dto.response.OwnerResponseDto;
import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.Owner;
import com.autoserviceapp.service.CarService;
import com.autoserviceapp.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OwnerDtoMapperImpl implements RequestDtoMapper<OwnerRequestDto, Owner>,
        ResponseDtoMapper<OwnerResponseDto, Owner> {
    private final OrderService orderService;
    private final CarService carService;

    public OwnerDtoMapperImpl(OrderService orderService, CarService carService) {
        this.orderService = orderService;
        this.carService = carService;
    }

    @Override
    public Owner mapToModel(OwnerRequestDto dto) {
        Owner owner = new Owner();
        owner.setOrders(orderService.getAllOrders(dto.getOrderIds()));
        owner.setCars(carService.getAllCars(dto.getCarIds()));
        return owner;
    }

    @Override
    public OwnerResponseDto mapToDto(Owner owner) {
        OwnerResponseDto responseDto = new OwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setOrderIds(owner.getOrders().stream()
                .map(Order::getId)
                .toList());
        responseDto.setCarIds(owner.getCars().stream()
                .map(Car::getId)
                .toList());
        return responseDto;
    }
}
