package com.autoserviceapp.controller;

import com.autoserviceapp.dto.mapper.impl.OrderDtoMapperImpl;
import com.autoserviceapp.dto.mapper.impl.OwnerDtoMapperImpl;
import com.autoserviceapp.dto.request.OwnerRequestDto;
import com.autoserviceapp.dto.response.OrderResponseDto;
import com.autoserviceapp.dto.response.OwnerResponseDto;
import com.autoserviceapp.model.Owner;
import com.autoserviceapp.service.OrderService;
import com.autoserviceapp.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerDtoMapperImpl ownerDtoMapper;
    private final OrderService orderService;
    private final OrderDtoMapperImpl orderDtoMapper;

    public OwnerController(OwnerService ownerService,
                           OwnerDtoMapperImpl ownerDtoMapper,
                           OrderService orderService,
                           OrderDtoMapperImpl orderDtoMapper) {
        this.ownerService = ownerService;
        this.ownerDtoMapper = ownerDtoMapper;
        this.orderService = orderService;
        this.orderDtoMapper = orderDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Creates new owner")
    public OwnerResponseDto save(@RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerService.save(ownerDtoMapper.mapToModel(requestDto));
        return ownerDtoMapper.mapToDto(owner);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates owner by id")
    public OwnerResponseDto update(@PathVariable Long id,
                                   @RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerDtoMapper.mapToModel(requestDto);
        owner.setId(id);
        return ownerDtoMapper.mapToDto(ownerService.update(owner));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns owner`s orders by id")
    public List<OrderResponseDto> getOwnersOrders(@PathVariable Long id) {
        return orderService.getOrdersByOwnerId(id)
                .stream()
                .map(orderDtoMapper::mapToDto)
                .toList();
    }
}
