package com.trongit.repository.custom;

import com.trongit.builder.BuildingSearchBuilder;
import com.trongit.dto.request.BuildingSearchRequest;
import com.trongit.entity.BuildingEntity;
import com.trongit.entity.UserEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
