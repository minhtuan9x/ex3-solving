package com.trongit.repository.custom;

import com.trongit.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepositoryCustom {
    void saveAllByBuilding(List<RentAreaEntity> rentAreaEntitis, List<RentAreaEntity> rentAreaEntityListByBuilding);
}
