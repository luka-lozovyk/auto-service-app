package com.autoserviceapp.repository;

import com.autoserviceapp.model.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SparePartRepository extends JpaRepository<SparePart, Long> {

}
