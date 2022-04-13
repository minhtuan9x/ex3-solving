package com.trongit.repository;

import com.trongit.entity.AssignmentBuildingEntity;
import com.trongit.entity.BuildingEntity;
import com.trongit.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity,Long> {
    AssignmentBuildingEntity findByBuildingEntityAndUserEntity(BuildingEntity buildingEntity, UserEntity userEntity);
    void deleteByIdIn(List<Long> ids);
}
