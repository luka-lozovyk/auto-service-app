package com.autoserviceapp.repository;

import com.autoserviceapp.model.Car;
import com.autoserviceapp.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCarIn(List<Car> cars);
}
