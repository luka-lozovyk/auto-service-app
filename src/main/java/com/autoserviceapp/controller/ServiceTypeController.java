package com.autoserviceapp.controller;

import com.autoserviceapp.dto.mapper.impl.ServiceTypeDtoMapperImpl;
import com.autoserviceapp.dto.request.ServiceTypeRequestDto;
import com.autoserviceapp.dto.response.ServiceTypeResponseDto;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.service.OrderService;
import com.autoserviceapp.service.ServiceTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-type")
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;
    private final ServiceTypeDtoMapperImpl serviceTypeDtoMapper;
    private final OrderService orderService;

    public ServiceTypeController(ServiceTypeService serviceTypeService,
                                 ServiceTypeDtoMapperImpl serviceTypeDtoMapper,
                                 OrderService orderService) {
        this.serviceTypeService = serviceTypeService;
        this.serviceTypeDtoMapper = serviceTypeDtoMapper;
        this.orderService = orderService;
    }

    @PostMapping
    @ApiOperation(value = "Creates new service type")
    public ServiceTypeResponseDto save(@RequestBody ServiceTypeRequestDto requestDto) {
        ServiceType serviceType = serviceTypeDtoMapper.mapToModel(requestDto);
        ServiceTypeResponseDto responseDto = serviceTypeDtoMapper.mapToDto(
                serviceTypeService.save(serviceType));

        Order orderToUpdate = orderService.getOrderById(requestDto.getOrderId());
        orderToUpdate.getServiceTypes().add(serviceType);
        orderService.update(orderToUpdate);

        return responseDto;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates service type by id")
    public ServiceTypeResponseDto update(@PathVariable Long id,
                                         @RequestBody ServiceTypeRequestDto requestDto) {
        ServiceType serviceType = serviceTypeDtoMapper.mapToModel(requestDto);
        serviceType.setId(id);
        ServiceType updated = serviceTypeService.update(serviceType);

        return serviceTypeDtoMapper.mapToDto(updated);
    }

    @PutMapping("/change-status/{id}")
    @ApiOperation(value = "Changes service type status by id")
    public ServiceTypeResponseDto changeStatus(@PathVariable Long id) {
        ServiceType serviceType = serviceTypeService.getServiceTypeById(id);
        serviceType.setId(id);
        serviceTypeService.changeStatus(serviceType);

        return serviceTypeDtoMapper.mapToDto(serviceTypeService.update(serviceType));
    }
}
