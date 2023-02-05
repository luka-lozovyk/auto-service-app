package com.autoserviceapp.service.impl;

import com.autoserviceapp.model.ServiceType;
import com.autoserviceapp.model.enums.SalaryStatus;
import com.autoserviceapp.repository.MechanicRepository;
import com.autoserviceapp.repository.ServiceTypeRepository;
import com.autoserviceapp.service.ServiceTypeService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;
    private final MechanicRepository mechanicRepository;

    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository,
                                  MechanicRepository mechanicRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public ServiceType save(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public ServiceType update(ServiceType serviceType) {
        return serviceTypeRepository.save(serviceType);
    }

    @Override
    public List<ServiceType> getServiceTypesByIds(List<Long> serviceTypeIds) {
        return serviceTypeRepository.findAllById(serviceTypeIds);
    }

    @Override
    public ServiceType getServiceTypeById(Long serviceTypeId) {
        return serviceTypeRepository.getById(serviceTypeId);
    }

    @Override
    public ServiceType changeStatus(ServiceType serviceType) {
        if (serviceType.getSalaryStatus().equals(SalaryStatus.PAID)) {
            serviceType.setSalaryStatus(SalaryStatus.NOT_PAID);
        }
        if (serviceType.getSalaryStatus().equals(SalaryStatus.NOT_PAID)) {
            serviceType.setSalaryStatus(SalaryStatus.PAID);
        }
        return serviceType;
    }

    @Override
    public List<ServiceType> findAllServiceTypeByMechanicId(Long mechanicId) {
        return serviceTypeRepository.findALlByMechanic(
                mechanicRepository.getById(mechanicId));
    }
}
