package com.autoserviceapp.service;

import com.autoserviceapp.model.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order save(Order order);

    Order addSparePartToOrder(Long orderId, List<Long> sparePartIds);

    Order update(Order order);

    List<Order> getAllOrders(List<Long> orderIds);

    Order getOrderById(Long orderId);

    BigDecimal calcTotalSum(Long orderId);

    List<Order> getOrdersByOwnerId(Long ownerId);
}
