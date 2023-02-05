package com.autoserviceapp.service;

import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.Order;
import java.util.List;

public interface MechanicService {
    Mechanic save(Mechanic mechanic);

    Mechanic update(Mechanic mechanic);

    List<Order> findMechanicCompletedOrders(Long mechanicId);

    Mechanic getMechanicById(Long mechanicId);

}
