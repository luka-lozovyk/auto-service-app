package com.autoserviceapp.service;

import com.autoserviceapp.model.Car;
import java.util.List;

public interface CarService {
    Car save(Car car);

    Car update(Car car);

    List<Car> getAllCars(List<Long> carIds);

    Car getCarById(Long carId);
}
