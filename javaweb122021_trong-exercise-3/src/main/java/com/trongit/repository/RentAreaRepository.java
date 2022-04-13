package com.trongit.repository;

import com.trongit.entity.BuildingEntity;
import com.trongit.entity.RentAreaEntity;
import com.trongit.repository.custom.RentAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends RentAreaRepositoryCustom, JpaRepository<RentAreaEntity,Long> {
    List<RentAreaEntity> findByBuildingEntity(BuildingEntity buildingEntity);
    void deleteByIdIn(List<Long> ids);
}
