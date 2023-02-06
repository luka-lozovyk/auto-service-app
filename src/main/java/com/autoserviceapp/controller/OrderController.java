package com.autoserviceapp.controller;

import com.autoserviceapp.dto.mapper.impl.OrderDtoMapperImpl;
import com.autoserviceapp.dto.request.OrderRequestDto;
import com.autoserviceapp.dto.response.OrderResponseDto;
import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.model.enums.OrderStatus;
import com.autoserviceapp.service.MechanicService;
import com.autoserviceapp.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderDtoMapperImpl orderDtoMapper;
    private final MechanicService mechanicService;

    public OrderController(OrderService orderService,
                           OrderDtoMapperImpl orderDtoMapper,
                           MechanicService mechanicService) {
        this.orderService = orderService;
        this.orderDtoMapper = orderDtoMapper;
        this.mechanicService = mechanicService;
    }

    @PostMapping
    @ApiOperation(value = "Creates new order")
    public OrderResponseDto save(@RequestBody OrderRequestDto requestDto) {
        Order order = orderDtoMapper.mapToModel(requestDto);
        return orderDtoMapper.mapToDto(orderService.save(order));
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "Adds spare parts to order")
    public OrderResponseDto addSparePartsToOrder(@PathVariable Long id,
                                                 @RequestParam List<Long> sparePartIds) {
        Order orderToUpdate = orderService.addSparePartToOrder(id, sparePartIds);
        orderToUpdate.setTotalPrice(orderService.calcTotalSum(id));
        return orderDtoMapper.mapToDto(orderService.update(orderToUpdate));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates order by id")
    public OrderResponseDto update(@PathVariable Long id,
                                   @RequestBody OrderRequestDto requestDto) {
        Order order = orderDtoMapper.mapToModel(requestDto);
        order.setId(id);
        order.setTotalPrice(orderService.calcTotalSum(id));
        return orderDtoMapper.mapToDto(orderService.update(order));
    }

    @PutMapping("/change-status/{id}")
    @ApiOperation(value = "Changes order status by id")
    public OrderResponseDto changeOrderStatus(@PathVariable Long id,
                                              @ApiParam("Possible params: Accepted, "
                                                      + "In_progress, "
                                                      + "Completed_successfully, "
                                                      + "Completed_unsuccessfully, "
                                                      + "Paid")
                                              @RequestParam String status) {
        Order order = orderService.getOrderById(id);
        order.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
        if (OrderStatus.valueOf(status.toUpperCase()).equals(OrderStatus.COMPLETED_SUCCESSFULLY)
                || OrderStatus.valueOf(status.toUpperCase())
                        .equals(OrderStatus.COMPLETED_UNSUCCESSFULLY)) {
            order.setFinishDate(LocalDateTime.now());
            List<Mechanic> mechanics = order.getServiceTypes().stream()
                    .map(ServiceType::getMechanic)
                    .toList();
            mechanics.forEach(m -> m.getCompletedOrders().add(order));
            mechanics.forEach(mechanicService::update);
            return orderDtoMapper.mapToDto(orderService.update(order));
        }
        return orderDtoMapper.mapToDto(orderService.update(order));
    }

    @GetMapping("/totalPrice/{id}")
    @ApiOperation(value = "Returns order total price")
    public BigDecimal getOrderTotalPrice(@PathVariable Long id) {
        return orderService.calcTotalSum(id);
    }
}
