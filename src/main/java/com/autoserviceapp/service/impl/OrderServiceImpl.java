package com.autoserviceapp.service.impl;

import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.Owner;
import com.autoserviceapp.model.SparePart;
import com.autoserviceapp.repository.OrderRepository;
import com.autoserviceapp.repository.OwnerRepository;
import com.autoserviceapp.repository.SparePartRepository;
import com.autoserviceapp.service.OrderService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private static final double SPARE_PART_DISCOUNT_PERCENT = 0.01;
    private static final double SERVICE_TYPE_DISCOUNT_PERCENT = 0.02;
    private final OrderRepository orderRepository;
    private final SparePartRepository sparePartRepository;
    private final OwnerRepository ownerRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            SparePartRepository sparePartRepository,
                            OwnerRepository ownerRepository) {
        this.orderRepository = orderRepository;
        this.sparePartRepository = sparePartRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order addSparePartToOrder(Long orderId, List<Long> sparePartIds) {
        List<SparePart> sparePartsToAdd = sparePartRepository.findAllById(sparePartIds);
        Order orderToModify = orderRepository.getById(orderId);
        orderToModify.getSpareParts().addAll(sparePartsToAdd);
        return orderToModify;
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders(List<Long> orderIds) {
        return orderRepository.findAllById(orderIds);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.getById(orderId);
    }

    @Override
    public BigDecimal calcTotalSum(Long orderId) {
        int ownerOrdersQuantity = getOrdersByOwnerId(orderRepository
                .getById(orderId).getCar().getOwner().getId()).size();
        Order order = orderRepository.getById(orderId);
        BigDecimal sparePartsPriceSum = order.getSpareParts()
                .stream()
                .map(sp -> (sp.getPrice()).subtract(sp.getPrice().multiply(BigDecimal
                        .valueOf(ownerOrdersQuantity * SPARE_PART_DISCOUNT_PERCENT))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal serviceTypesPriceSum = order.getServiceTypes()
                .stream()
                .map(st -> (st.getPrice()).subtract(st.getPrice().multiply(BigDecimal
                                .valueOf(ownerOrdersQuantity * SERVICE_TYPE_DISCOUNT_PERCENT))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sparePartsPriceSum.add(serviceTypesPriceSum);
    }

    @Override
    public List<Order> getOrdersByOwnerId(Long ownerId) {
        Owner owner = ownerRepository.getById(ownerId);
        return orderRepository.findAllByCarIn(owner.getCars());
    }
}
