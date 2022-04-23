package com.trongit.repository;

import com.trongit.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {

    void deleteAllByBuildingEntity_IdIn(List<Long> buildingIds);
}
