package com.autoserviceapp.dto.mapper.impl;

import com.autoserviceapp.dto.request.MechanicRequestDto;
import com.autoserviceapp.dto.response.MechanicResponseDto;
import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.enums.OrderStatus;
import com.autoserviceapp.service.MechanicService;
import com.autoserviceapp.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MechanicDtoMapperImplTest {
    private static final MechanicRequestDto TEST_MECHANIC_REQUEST_DTO =
            new MechanicRequestDto("Andrii", List.of(1L, 2L));
    private static final List<Order> MOCK_ORDERS = List.of(new Order(1L, new Car(), "Description",
            LocalDateTime.now(), Collections.emptyList(), Collections.emptyList(), OrderStatus.ACCEPTED,
            BigDecimal.valueOf(1000), LocalDateTime.now()));
    private static final Mechanic TEST_MECHANIC_MODEL = new Mechanic(1L,"Bob", MOCK_ORDERS);

    @InjectMocks
    private MechanicDtoMapperImpl mechanicDtoMapper;
    @Mock
    private OrderService orderService;
    @Mock
    private MechanicService mechanicService;

    @Test
    void shouldMapMechanicModelFromDto() {
        Mockito.when(orderService.getAllOrders(TEST_MECHANIC_REQUEST_DTO.getCompletedOrderIds()))
                .thenReturn(MOCK_ORDERS);

        Mechanic expected = new Mechanic(null, TEST_MECHANIC_REQUEST_DTO.getFullName(),
                orderService.getAllOrders(TEST_MECHANIC_REQUEST_DTO.getCompletedOrderIds()));
        Mechanic actual = mechanicDtoMapper.mapToModel(TEST_MECHANIC_REQUEST_DTO);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getCompletedOrders(), expected.getCompletedOrders());
    }

    @Test
    void shouldMapMechanicResponseDtoFromModel() {
        Mockito.when(mechanicService.findMechanicCompletedOrders(1L))
                .thenReturn(MOCK_ORDERS);

        MechanicResponseDto expected = new MechanicResponseDto(
                TEST_MECHANIC_MODEL.getId(), TEST_MECHANIC_MODEL.getFullName(),
                TEST_MECHANIC_MODEL.getCompletedOrders().stream().map(Order::getId).toList());
        MechanicResponseDto actual = mechanicDtoMapper.mapToDto(TEST_MECHANIC_MODEL);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getFullName(), expected.getFullName());
        Assertions.assertEquals(actual.getCompletedOrderIds(), expected.getCompletedOrderIds());
    }
}