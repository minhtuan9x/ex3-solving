package com.trongit.repository;

import com.trongit.entity.BuildingEntity;
import com.trongit.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends BuildingRepositoryCustom, JpaRepository<BuildingEntity, Long> {
    BuildingEntity findById(Long id);
    void deleteAllByIdIn(List<Long> ids);
}
