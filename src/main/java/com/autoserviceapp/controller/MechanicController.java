package com.autoserviceapp.controller;

import com.autoserviceapp.dto.mapper.impl.MechanicDtoMapperImpl;
import com.autoserviceapp.dto.mapper.impl.OrderDtoMapperImpl;
import com.autoserviceapp.dto.request.MechanicRequestDto;
import com.autoserviceapp.dto.response.MechanicResponseDto;
import com.autoserviceapp.dto.response.OrderResponseDto;
import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.enums.SalaryStatus;
import com.autoserviceapp.service.MechanicService;
import com.autoserviceapp.service.ServiceTypeService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mechanic")
public class MechanicController {

    private final MechanicService mechanicService;
    private final MechanicDtoMapperImpl mechanicDtoMapper;
    private final ServiceTypeService serviceTypeService;
    private final OrderDtoMapperImpl orderDtoMapper;

    public MechanicController(MechanicService mechanicService,
                              MechanicDtoMapperImpl mechanicDtoMapper,
                              ServiceTypeService serviceTypeService,
                              OrderDtoMapperImpl orderDtoMapper) {
        this.mechanicService = mechanicService;
        this.mechanicDtoMapper = mechanicDtoMapper;
        this.serviceTypeService = serviceTypeService;
        this.orderDtoMapper = orderDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Creates new mechanic")
    public MechanicResponseDto save(@RequestBody MechanicRequestDto requestDto) {
        Mechanic mechanic = mechanicService.save(mechanicDtoMapper.mapToModel(requestDto));
        return mechanicDtoMapper.mapToDto(mechanic);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates mechanic by id")
    public MechanicResponseDto update(@PathVariable Long id,
                                      @RequestBody MechanicRequestDto requestDto) {
        Mechanic mechanic = mechanicDtoMapper.mapToModel(requestDto);
        mechanic.setId(id);
        return mechanicDtoMapper.mapToDto(mechanicService.update(mechanic));
    }

    @GetMapping("/completedOrders/{id}")
    @ApiOperation(value = "Returns mechanic`s completed orders by id")
    public List<OrderResponseDto> getMechanicsCompletedOrders(@PathVariable Long id) {
        return mechanicService.getMechanicById(id).getCompletedOrders().stream()
                .map(orderDtoMapper::mapToDto).toList();
    }

    @GetMapping("/salary/{id}")
    @ApiOperation(value = "Returns mechanic`s salary by id")
    public BigDecimal calculateMechanicSalary(@PathVariable Long id) {
        BigDecimal mechanicSalary = serviceTypeService.findAllServiceTypeByMechanicId(id)
                .stream()
                .filter(st -> st.getSalaryStatus().equals(SalaryStatus.NOT_PAID))
                .map(st -> st.getPrice().multiply(BigDecimal.valueOf(0.4)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        serviceTypeService.findAllServiceTypeByMechanicId(id)
                .stream()
                .filter(st -> st.getSalaryStatus().equals(SalaryStatus.NOT_PAID))
                .map(serviceTypeService::changeStatus)
                .map(serviceTypeService::update)
                .toList();
        return mechanicSalary;
    }
}
