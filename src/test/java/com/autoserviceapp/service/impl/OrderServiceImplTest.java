package com.autoserviceapp.service.impl;

import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.Owner;
import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.model.SparePart;
import com.autoserviceapp.model.enums.OrderStatus;
import com.autoserviceapp.repository.OrderRepository;
import com.autoserviceapp.repository.OwnerRepository;
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
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

//    private static final Owner OWNER = new Owner(1L, List.of(), List.of());
//    private static final Car CAR = new Car(33L, "Porsche",
//            "911", 2012, "7777", OWNER);
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OwnerRepository ownerRepository;

    @Test
    void shouldCalcOrderTotalSum() {
        Owner owner = new Owner(1L, Collections.emptyList(), Collections.emptyList());
        Car car = new Car(33L, "Porsche",
                "911", 2012, "7777", owner);
        owner.setCars(List.of(car));
        List<ServiceType> serviceTypes = List.of(new ServiceType(1L, BigDecimal.valueOf(100)),
                new ServiceType(2L, BigDecimal.valueOf(200)));
        List<SparePart> spareParts = List.of(new SparePart(1L, "Oil", BigDecimal.valueOf(100)),
                new SparePart(2L, "Filter", BigDecimal.valueOf(120)));

        Order orderToCalc = new Order(1L, car, "problem", LocalDateTime.now(),
                serviceTypes, spareParts, OrderStatus.ACCEPTED, BigDecimal.ZERO, LocalDateTime.now());
        Mockito.when(orderRepository.getById(1L)).thenReturn(orderToCalc);
        Mockito.when(orderService.getOrderById(1L)).thenReturn(orderToCalc);
        Mockito.when(ownerRepository.getById(1L)).thenReturn(owner);
        BigDecimal expected = BigDecimal.valueOf(520.0);
        BigDecimal actual = orderService.calcTotalSum(1L);

        Assertions.assertEquals(expected, actual);
    }
    //calcTotalSum

}
