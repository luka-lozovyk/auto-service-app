package com.autoserviceapp.dto.mapper.impl;


import com.autoserviceapp.dto.request.OrderRequestDto;
import com.autoserviceapp.dto.response.OrderResponseDto;
import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.Owner;
import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.model.SparePart;
import com.autoserviceapp.model.enums.OrderStatus;
import com.autoserviceapp.service.CarService;
import com.autoserviceapp.service.OrderService;
import com.autoserviceapp.service.ServiceTypeService;
import com.autoserviceapp.service.SparePartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class OrderDtoMapperImplTest {
    private static final OrderRequestDto TEST_ORDER_REQUEST_DTO =
            new OrderRequestDto(1L, "Some problem", LocalDateTime.now(),
                    Collections.emptyList(), Collections.emptyList(),
                    OrderStatus.ACCEPTED.toString(), BigDecimal.valueOf(1200L), LocalDateTime.now());
    private static final Order TEST_ORDER_MODEL = new Order(33L,new Car(1L),
            "Some description",LocalDateTime.now(), Collections.emptyList(),
            Collections.emptyList(),OrderStatus.ACCEPTED,BigDecimal.valueOf(0),
            LocalDateTime.now());
    @InjectMocks
    private OrderDtoMapperImpl orderDtoMapper;
    @Mock
    private CarService carService;
    @Mock
    private ServiceTypeService serviceTypeService;
    @Mock
    private SparePartService sparePartService;
    @Mock
    private OrderService orderService;

    @Test
    void shouldMapOrderModelFromDto() {
        Mockito.when(carService.getCarById(1L)).thenReturn(new Car(1L, "Porsche",
                "911", 2012, "7777", new Owner()));
        Mockito.when(serviceTypeService.getServiceTypesByIds(TEST_ORDER_REQUEST_DTO
                        .getServiceTypeIds()))
                .thenReturn(Collections.emptyList());
        Mockito.when(sparePartService.getSparePartsByIds(TEST_ORDER_REQUEST_DTO
                        .getSparePartIds()))
                .thenReturn(Collections.emptyList());

        Order expected = new Order(null, carService.getCarById(1L),
                TEST_ORDER_REQUEST_DTO.getProblemDescription(),
                TEST_ORDER_REQUEST_DTO.getStartDate(),
                serviceTypeService.getServiceTypesByIds(TEST_ORDER_REQUEST_DTO
                        .getServiceTypeIds()),
                sparePartService.getSparePartsByIds(TEST_ORDER_REQUEST_DTO
                        .getSparePartIds()),
                OrderStatus.valueOf(TEST_ORDER_REQUEST_DTO.getOrderStatus().toUpperCase()),
                TEST_ORDER_REQUEST_DTO.getTotalPrice(), TEST_ORDER_REQUEST_DTO.getFinishDate());
        Order actual = orderDtoMapper.mapToModel(TEST_ORDER_REQUEST_DTO);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getCar(), expected.getCar());
        Assertions.assertEquals(actual.getProblemDescription(), expected.getProblemDescription());
        Assertions.assertEquals(actual.getStartDate(), expected.getStartDate());
        Assertions.assertEquals(actual.getServiceTypes(), expected.getServiceTypes());
        Assertions.assertEquals(actual.getSpareParts(), expected.getSpareParts());
        Assertions.assertEquals(actual.getOrderStatus(), expected.getOrderStatus());
        Assertions.assertEquals(actual.getTotalPrice(), expected.getTotalPrice());
        Assertions.assertEquals(actual.getOrderStatus(), expected.getOrderStatus());
        Assertions.assertEquals(actual.getFinishDate(), expected.getFinishDate());
    }

    @Test
    void shouldMapOrderResponseDtoFromModel() {
        Mockito.when(orderService.calcTotalSum(TEST_ORDER_MODEL.getId())).thenReturn(BigDecimal.ZERO);

        OrderResponseDto expected = new OrderResponseDto(TEST_ORDER_MODEL.getId(),
                TEST_ORDER_MODEL.getCar().getId(), TEST_ORDER_MODEL.getProblemDescription(),
                TEST_ORDER_MODEL.getStartDate(),
                TEST_ORDER_MODEL.getServiceTypes().stream().map(ServiceType::getId).toList(),
                TEST_ORDER_MODEL.getSpareParts().stream().map(SparePart::getId).toList(),
                TEST_ORDER_MODEL.getOrderStatus().toString(),
                TEST_ORDER_MODEL.getTotalPrice(), TEST_ORDER_MODEL.getFinishDate());
        OrderResponseDto actual = orderDtoMapper.mapToDto(TEST_ORDER_MODEL);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getCarId(), expected.getCarId());
        Assertions.assertEquals(actual.getProblemDescription(), expected.getProblemDescription());
        Assertions.assertEquals(actual.getStartDate(), expected.getStartDate());
        Assertions.assertEquals(actual.getServiceTypeIds(), expected.getServiceTypeIds());
        Assertions.assertEquals(actual.getSparePartIds(), expected.getSparePartIds());
        Assertions.assertEquals(actual.getOrderStatus(), expected.getOrderStatus());
        Assertions.assertEquals(actual.getTotalPrice(), expected.getTotalPrice());
        Assertions.assertEquals(actual.getFinishDate(), expected.getFinishDate());
    }
}