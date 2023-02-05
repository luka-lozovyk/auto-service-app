package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.mapper.RequestDtoMapper;
import com.autoserviceapp.dto.mapper.ResponseDtoMapper;
import com.autoserviceapp.dto.request.MechanicRequestDto;
import com.autoserviceapp.dto.response.MechanicResponseDto;
import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.service.MechanicService;
import com.autoserviceapp.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class MechanicDtoMapperImpl implements RequestDtoMapper<MechanicRequestDto, Mechanic>,
        ResponseDtoMapper<MechanicResponseDto, Mechanic> {

    private final OrderService orderService;
    private final MechanicService mechanicService;

    public MechanicDtoMapperImpl(OrderService orderService, MechanicService mechanicService) {
        this.orderService = orderService;
        this.mechanicService = mechanicService;
    }

    @Override
    public Mechanic mapToModel(MechanicRequestDto dto) {
        Mechanic mechanic = new Mechanic();
        mechanic.setFullName(dto.getFullName());
        mechanic.setCompletedOrders(orderService.getAllOrders(dto.getCompletedOrderIds()));
        return mechanic;
    }

    @Override
    public MechanicResponseDto mapToDto(Mechanic mechanic) {
        MechanicResponseDto responseDto = new MechanicResponseDto();
        responseDto.setId(mechanic.getId());
        responseDto.setFullName(mechanic.getFullName());
        responseDto.setCompletedOrderIds(mechanicService.findMechanicCompletedOrders(
                mechanic.getId())
                .stream().map(Order::getId)
                .toList());
        return responseDto;
    }
}
