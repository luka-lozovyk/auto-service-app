package com.autoserviceapp.service.impl;

import com.autoserviceapp.model.Car;
import com.autoserviceapp.repository.CarRepository;
import com.autoserviceapp.service.CarService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars(List<Long> carIds) {
        return carRepository.findAllById(carIds);
    }

    @Override
    public Car getCarById(Long carId) {
        return carRepository.getById(carId);
    }
}
