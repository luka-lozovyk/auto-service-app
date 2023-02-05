package com.autoserviceapp.repository;

import com.autoserviceapp.model.Mechanic;
import com.autoserviceapp.model.ServiceType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
    List<ServiceType> findALlByMechanic(Mechanic mechanic);
}
