package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.request.OwnerRequestDto;
import com.autoserviceapp.dto.response.OwnerResponseDto;
import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.Owner;
import com.autoserviceapp.service.CarService;
import com.autoserviceapp.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class OwnerDtoMapperImplTest {
    private static final OwnerRequestDto TEST_OWNER_REQUEST_DTO =
            new OwnerRequestDto(List.of(), List.of());
    private static final Owner TEST_OWNER_MODEL = new Owner(22L, List.of(), Collections.emptyList());

    @InjectMocks
    private OwnerDtoMapperImpl ownerDtoMapper;
    @Mock
    private OrderService orderService;
    @Mock
    private CarService carService;

    @Test
    void shouldMapOwnerModelFromDto() {
        Mockito.when(carService.getAllCars(TEST_OWNER_REQUEST_DTO.getOrderIds()))
                        .thenReturn(List.of());
        Mockito.when(orderService.getAllOrders(TEST_OWNER_REQUEST_DTO.getOrderIds()))
                .thenReturn(Collections.emptyList());

        Owner expected = new Owner(null, carService.getAllCars(TEST_OWNER_REQUEST_DTO.getCarIds()),
                orderService.getAllOrders(TEST_OWNER_REQUEST_DTO.getOrderIds()));
        Owner actual = ownerDtoMapper.mapToModel(TEST_OWNER_REQUEST_DTO);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getCars(), expected.getCars());
        Assertions.assertEquals(actual.getOrders(), expected.getOrders());
    }

    @Test
    void shouldMapOwnerResponseDtoFromModel() {
        OwnerResponseDto expected = new OwnerResponseDto(TEST_OWNER_MODEL.getId(),
                TEST_OWNER_MODEL.getCars().stream().map(Car::getId).toList(),
                TEST_OWNER_MODEL.getOrders().stream().map(Order::getId).toList());
        OwnerResponseDto actual = ownerDtoMapper.mapToDto(TEST_OWNER_MODEL);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getCarIds(), expected.getCarIds());
        Assertions.assertEquals(actual.getOrderIds(), expected.getOrderIds());
    }
}