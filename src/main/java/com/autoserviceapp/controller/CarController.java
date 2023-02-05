package com.autoserviceapp.controller;

import com.autoserviceapp.dto.mapper.impl.CarDtoMapperImpl;
import com.autoserviceapp.dto.request.CarRequestDto;
import com.autoserviceapp.dto.response.CarResponseDto;
import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Owner;
import com.autoserviceapp.service.CarService;
import com.autoserviceapp.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    private final CarDtoMapperImpl carDtoMapper;
    private final OwnerService ownerService;

    public CarController(CarService carService,
                         CarDtoMapperImpl carDtoMapper,
                         OwnerService ownerService) {
        this.carService = carService;
        this.carDtoMapper = carDtoMapper;
        this.ownerService = ownerService;
    }

    /*
     POST - Создание сущности «Машина» +
• PUT - Редактирование данных сущности+
     */

    @PostMapping
    @ApiOperation(value = "Creates new car")
    public CarResponseDto save(@RequestBody CarRequestDto carRequestDto) {
        Car car = carService.save(carDtoMapper.mapToModel(carRequestDto));

        Owner owner = ownerService.getOwnerById(carRequestDto.getOwnerId());
        owner.getCars().add(car);
        ownerService.update(owner);

        return carDtoMapper.mapToDto(car);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates car by id")
    public CarResponseDto update(@PathVariable Long id,
                                 @RequestBody CarRequestDto carRequestDto) {
        Car car = carDtoMapper.mapToModel(carRequestDto);
        car.setId(id);
        carService.update(car);
        return carDtoMapper.mapToDto(car);
    }
}
