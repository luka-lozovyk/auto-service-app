package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.mapper.RequestDtoMapper;
import com.autoserviceapp.dto.mapper.ResponseDtoMapper;
import com.autoserviceapp.dto.request.OrderRequestDto;
import com.autoserviceapp.dto.response.OrderResponseDto;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.model.SparePart;
import com.autoserviceapp.model.enums.OrderStatus;
import com.autoserviceapp.service.CarService;
import com.autoserviceapp.service.OrderService;
import com.autoserviceapp.service.ServiceTypeService;
import com.autoserviceapp.service.SparePartService;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoMapperImpl implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    private final CarService carService;
    private final ServiceTypeService serviceTypeService;
    private final SparePartService sparePartService;
    private final OrderService orderService;

    public OrderDtoMapperImpl(CarService carService,
                              ServiceTypeService serviceTypeService,
                              SparePartService sparePartService,
                              OrderService orderService) {
        this.carService = carService;
        this.serviceTypeService = serviceTypeService;
        this.sparePartService = sparePartService;
        this.orderService = orderService;
    }

    @Override
    public Order mapToModel(OrderRequestDto dto) {
        Order order = new Order();
        order.setCar(carService.getCarById(dto.getCarId()));
        order.setProblemDescription(dto.getProblemDescription());
        order.setStartDate(dto.getStartDate());
        order.setServiceTypes(serviceTypeService.getServiceTypesByIds(dto.getServiceTypeIds()));
        order.setSpareParts(sparePartService.getSparePartsByIds(dto.getSparePartIds()));
        order.setOrderStatus(OrderStatus.valueOf(dto.getOrderStatus().toUpperCase()));
        order.setTotalPrice(dto.getTotalPrice());
        order.setFinishDate(dto.getFinishDate());
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setCarId(order.getCar().getId());
        responseDto.setProblemDescription(order.getProblemDescription());
        responseDto.setStartDate(order.getStartDate());
        responseDto.setServiceTypeIds(order.getServiceTypes().stream()
                .map(ServiceType::getId)
                .toList());
        responseDto.setSparePartIds(order.getSpareParts().stream()
                .map(SparePart::getId)
                .toList());
        responseDto.setOrderStatus(order.getOrderStatus().toString().toUpperCase());
        responseDto.setTotalPrice(orderService.calcTotalSum(order.getId()));
        responseDto.setFinishDate(order.getFinishDate());
        return responseDto;
    }
}
