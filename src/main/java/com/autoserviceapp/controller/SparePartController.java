package com.autoserviceapp.controller;

import com.autoserviceapp.dto.mapper.impl.SparePartDtoMapperImpl;
import com.autoserviceapp.dto.request.SparePartRequestDto;
import com.autoserviceapp.dto.response.SparePartResponseDto;
import com.autoserviceapp.model.SparePart;
import com.autoserviceapp.service.OrderService;
import com.autoserviceapp.service.SparePartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spare-part")
public class SparePartController {
    private final SparePartService sparePartService;
    private final SparePartDtoMapperImpl sparePartDtoMapper;
    private final OrderService orderService;

    public SparePartController(SparePartService sparePartService,
                               SparePartDtoMapperImpl sparePartDtoMapper,
                               OrderService orderService) {
        this.sparePartService = sparePartService;
        this.sparePartDtoMapper = sparePartDtoMapper;
        this.orderService = orderService;
    }

    @PostMapping
    @ApiOperation(value = "Creates new spare part")
    public SparePartResponseDto save(@RequestBody SparePartRequestDto requestDto) {
        SparePart sparePart = sparePartService.create(
                sparePartDtoMapper.mapToModel(requestDto));
        return sparePartDtoMapper.mapToDto(sparePart);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates spare part by id")
    public SparePartResponseDto update(@PathVariable Long id,
                                       @RequestBody SparePartRequestDto requestDto) {
        SparePart sparePart = sparePartDtoMapper.mapToModel(requestDto);
        sparePart.setId(id);
        SparePart updated = sparePartService.update(sparePart);
        return sparePartDtoMapper.mapToDto(updated);
    }
}
