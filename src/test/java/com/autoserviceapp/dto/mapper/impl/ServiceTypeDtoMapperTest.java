package com.autoserviceapp.dto.mapper.impl;


import com.autoserviceapp.dto.request.ServiceTypeRequestDto;
import com.autoserviceapp.dto.response.ServiceTypeResponseDto;
import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.model.enums.OrderStatus;
import com.autoserviceapp.model.enums.SalaryStatus;
import com.autoserviceapp.service.MechanicService;
import com.autoserviceapp.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ServiceTypeDtoMapperTest {
    private static final ServiceTypeRequestDto TEST_SERVICE_TYPE_REQUEST_DTO =
            new ServiceTypeRequestDto(1L, 1L, BigDecimal.valueOf(1000), "Not_paid");
    private static final ServiceType TEST_SERVICE_TYPE_MODEL =
            new ServiceType(44L, new Order(1L), new Mechanic(1L), BigDecimal.valueOf(100),
                    SalaryStatus.NOT_PAID);

    @InjectMocks
    private ServiceTypeDtoMapperImpl serviceTypeDtoMapper;
    @Mock
    private OrderService orderService;
    @Mock
    private MechanicService mechanicService;

    @Test
    void shouldMapServiceTypeModelFromDto() {
        Mockito.when(orderService.getOrderById(TEST_SERVICE_TYPE_REQUEST_DTO.getOrderId()))
                .thenReturn(new Order(1L, new Car(), "some description",
                        LocalDateTime.now(), List.of(), List.of(), OrderStatus.ACCEPTED,
                        BigDecimal.valueOf(0), LocalDateTime.now()));
        Mockito.when(mechanicService.getMechanicById(1L))
                .thenReturn( new Mechanic(1L, "Bob", List.of()));

        ServiceType expected = new ServiceType(null,
                orderService.getOrderById(TEST_SERVICE_TYPE_REQUEST_DTO.getOrderId()),
                mechanicService.getMechanicById(TEST_SERVICE_TYPE_REQUEST_DTO.getMechanicId()),
                TEST_SERVICE_TYPE_REQUEST_DTO.getPrice(),
                SalaryStatus.valueOf(TEST_SERVICE_TYPE_REQUEST_DTO.getSalaryStatus()
                        .toUpperCase()));
        ServiceType actual = serviceTypeDtoMapper.mapToModel(TEST_SERVICE_TYPE_REQUEST_DTO);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getMechanic(), expected.getMechanic());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
        Assertions.assertEquals(actual.getSalaryStatus(), expected.getSalaryStatus());
    }

    @Test
    void shouldMapServiceTypeResponseDtoFromModel() {
        ServiceTypeResponseDto expected = new ServiceTypeResponseDto(TEST_SERVICE_TYPE_MODEL.getId(),
                TEST_SERVICE_TYPE_MODEL.getOrder().getId(), TEST_SERVICE_TYPE_MODEL.getMechanic().getId(),
                TEST_SERVICE_TYPE_MODEL.getPrice(), TEST_SERVICE_TYPE_MODEL.getSalaryStatus().toString());
        ServiceTypeResponseDto actual = serviceTypeDtoMapper.mapToDto(TEST_SERVICE_TYPE_MODEL);

        Assertions.assertEquals(actual.getId(), expected.getId());
        Assertions.assertEquals(actual.getOrderId(), expected.getOrderId());
        Assertions.assertEquals(actual.getMechanicId(), expected.getMechanicId());
        Assertions.assertEquals(actual.getPrice(), expected.getPrice());
        Assertions.assertEquals(actual.getSalaryStatus(), expected.getSalaryStatus());
    }

}