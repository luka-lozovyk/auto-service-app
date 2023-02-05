package com.autoserviceapp.service;

import com.autoserviceapp.model.Owner;

public interface OwnerService {
    Owner save(Owner owner);

    Owner update(Owner owner);

    Owner getOwnerById(Long ownerId);
}
