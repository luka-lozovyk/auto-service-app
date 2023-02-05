package com.autoserviceapp.service;

import com.autoserviceapp.model.ServiceType;
import java.util.List;

public interface ServiceTypeService {
    ServiceType save(ServiceType serviceType);

    ServiceType update(ServiceType serviceType);

    List<ServiceType> getServiceTypesByIds(List<Long> serviceTypeIds);

    ServiceType getServiceTypeById(Long serviceTypeId);

    ServiceType changeStatus(ServiceType serviceType);

    List<ServiceType> findAllServiceTypeByMechanicId(Long mechanicId);

}
