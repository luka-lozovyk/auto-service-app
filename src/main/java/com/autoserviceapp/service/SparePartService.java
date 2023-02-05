package com.autoserviceapp.service;

import com.autoserviceapp.model.SparePart;
import java.util.List;

public interface SparePartService {
    SparePart create(SparePart part);

    SparePart update(SparePart part);

    List<SparePart> getSparePartsByIds(List<Long> sparePartIds);
}
