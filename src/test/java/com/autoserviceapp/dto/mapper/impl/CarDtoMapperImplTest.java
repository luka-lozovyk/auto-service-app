package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.request.CarRequestDto;
import com.autoserviceapp.dto.response.CarResponseDto;
import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Owner;
import com.autoserviceapp.service.impl.OwnerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarDtoMapperImplTest{
    private static final CarRequestDto TEST_CAR_REQUEST_DTO = new CarRequestDto("VW",
            "Jetta 2", 1991, "3790", 1L);
    private static final Owner TEST_OWNER = new Owner(1L,null, null);
    private static final Car TEST_CAR_MODEL = new Car(33L, "Porsche",
            "911", 2012, "7777", TEST_OWNER);
    @InjectMocks
    private CarDtoMapperImpl carDtoMapper;
    @Mock
    private OwnerServiceImpl ownerService;


    @Test
    void shouldMapCarModelFromDto() {
        Mockito.when(ownerService.getOwnerById(TEST_CAR_REQUEST_DTO.getOwnerId()))
                .thenReturn(TEST_OWNER);

        Car expected = new Car(null, TEST_CAR_REQUEST_DTO.getManufacturer(),
                TEST_CAR_REQUEST_DTO.getModel(), TEST_CAR_REQUEST_DTO.getYear(),
                TEST_CAR_REQUEST_DTO.getRegistrationNumber(),
                ownerService.getOwnerById(TEST_CAR_REQUEST_DTO.getOwnerId()));
        Car actual = carDtoMapper.mapToModel(TEST_CAR_REQUEST_DTO);

        Assertions.assertEquals(actual.getManufacturer(), expected.getManufacturer());
        Assertions.assertEquals(actual.getModel(), expected.getModel());
        Assertions.assertEquals(actual.getYear(), expected.getYear());
        Assertions.assertEquals(actual.getRegistrationNumber(), expected.getRegistrationNumber());
        Assertions.assertEquals(actual.getOwner(), expected.getOwner());
    }

    @Test
    void shouldMapCarResponseDtoFromModel() {
        CarResponseDto expected = new CarResponseDto(TEST_CAR_MODEL.getId(),
                TEST_CAR_MODEL.getManufacturer(), TEST_CAR_MODEL.getModel(),
                TEST_CAR_MODEL.getYear(), TEST_CAR_MODEL.getRegistrationNumber(),
                TEST_CAR_MODEL.getOwner().getId());
        CarResponseDto actual = carDtoMapper.mapToDto(TEST_CAR_MODEL);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getManufacturer(), expected.getManufacturer());
        Assertions.assertEquals(actual.getModel(), expected.getModel());
        Assertions.assertEquals(actual.getYear(), expected.getYear());
        Assertions.assertEquals(actual.getRegistrationNumber(), expected.getRegistrationNumber());
        Assertions.assertEquals(actual.getOwnerId(), expected.getOwnerId());
    }
}