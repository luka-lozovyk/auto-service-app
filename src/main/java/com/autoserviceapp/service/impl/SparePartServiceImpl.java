package com.autoserviceapp.service.impl;

import com.autoserviceapp.model.SparePart;
import com.autoserviceapp.repository.SparePartRepository;
import com.autoserviceapp.service.SparePartService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SparePartServiceImpl implements SparePartService {
    private final SparePartRepository sparePartRepository;

    public SparePartServiceImpl(SparePartRepository sparePartRepository) {
        this.sparePartRepository = sparePartRepository;
    }

    @Override
    public SparePart create(SparePart part) {
        return sparePartRepository.save(part);
    }

    @Override
    public SparePart update(SparePart part) {
        return sparePartRepository.save(part);
    }

    @Override
    public List<SparePart> getSparePartsByIds(List<Long> sparePartIds) {
        return sparePartRepository.findAllById(sparePartIds);
    }

}
