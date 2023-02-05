package com.autoserviceapp.service.impl;

import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.Order;
import com.autoserviceapp.repository.MechanicRepository;
import com.autoserviceapp.service.MechanicService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MechanicServiceImpl implements MechanicService {
    private final MechanicRepository mechanicRepository;

    public MechanicServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic update(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public List<Order> findMechanicCompletedOrders(Long mechanicId) {
        return mechanicRepository.getById(mechanicId).getCompletedOrders();
    }

    @Override
    public Mechanic getMechanicById(Long mechanicId) {
        return mechanicRepository.getById(mechanicId);
    }
}
