package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.mapper.RequestDtoMapper;
import com.autoserviceapp.dto.mapper.ResponseDtoMapper;
import com.autoserviceapp.dto.request.ServiceTypeRequestDto;
import com.autoserviceapp.dto.response.ServiceTypeResponseDto;
import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.model.enums.SalaryStatus;
import com.autoserviceapp.service.MechanicService;
import com.autoserviceapp.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class ServiceTypeDtoMapperImpl implements
        RequestDtoMapper<ServiceTypeRequestDto, ServiceType>,
        ResponseDtoMapper<ServiceTypeResponseDto, ServiceType> {
    private final OrderService orderService;
    private final MechanicService mechanicService;

    public ServiceTypeDtoMapperImpl(OrderService orderService, MechanicService mechanicService) {
        this.orderService = orderService;
        this.mechanicService = mechanicService;
    }

    @Override
    public ServiceType mapToModel(ServiceTypeRequestDto dto) {
        ServiceType serviceType = new ServiceType();
        serviceType.setOrder(orderService.getOrderById(dto.getOrderId()));
        serviceType.setMechanic(mechanicService.getMechanicById(dto.getMechanicId()));
        serviceType.setPrice(dto.getPrice());
        serviceType.setSalaryStatus(SalaryStatus.valueOf(dto.getSalaryStatus().toUpperCase()));
        return serviceType;

    }

    @Override
    public ServiceTypeResponseDto mapToDto(ServiceType serviceType) {
        ServiceTypeResponseDto responseDto = new ServiceTypeResponseDto();
        responseDto.setId(serviceType.getId());
        responseDto.setOrderId(serviceType.getOrder().getId());
        responseDto.setMechanicId(serviceType.getMechanic().getId());
        responseDto.setPrice(serviceType.getPrice());
        responseDto.setSalaryStatus(serviceType.getSalaryStatus().toString().toUpperCase());
        return responseDto;
    }
}
