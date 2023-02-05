package com.autoserviceapp.service.impl;

import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.model.enums.SalaryStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class ServiceTypeServiceImplTest {
    @InjectMocks
    private ServiceTypeServiceImpl serviceTypeService;

    @Test
    void shouldChangeStatus() {
        ServiceType serviceType = new ServiceType(1L, new Order(1L), new Mechanic(1L),
                BigDecimal.valueOf(1000), SalaryStatus.NOT_PAID);
        serviceTypeService.changeStatus(serviceType);
        Assertions.assertEquals(serviceType.getSalaryStatus(), SalaryStatus.PAID);
    }
}