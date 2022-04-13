package com.trongit.repository.custom.impl;

import com.trongit.entity.RentAreaEntity;
import com.trongit.repository.custom.RentAreaRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void saveAllByBuilding(List<RentAreaEntity> rentAreaEntitis, List<RentAreaEntity> rentAreaEntityListByBuilding) {
        rentAreaEntityListByBuilding.forEach(item -> entityManager.remove(item));
        rentAreaEntitis.forEach(item -> entityManager.persist(item));
    }
}
