package com.trongit.service.impl;

import com.trongit.converter.BuildingConverter;
import com.trongit.converter.RentAreaConverter;
import com.trongit.dto.BuildingDTO;
import com.trongit.dto.RentAreaDTO;
import com.trongit.entity.BuildingEntity;
import com.trongit.entity.RentAreaEntity;
import com.trongit.repository.BuildingRepository;
import com.trongit.repository.RentAreaRepository;
import com.trongit.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RentAreaServiceImpl implements RentAreaService {

    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public void saveAllByBuilding(List<RentAreaDTO> rentAreaDTOS, BuildingDTO buildingDTO) {
        List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
        for (RentAreaDTO item : rentAreaDTOS) {
            RentAreaEntity rentAreaEntity = rentAreaConverter.toRentAreaEntity(item);
            rentAreaEntities.add(rentAreaEntity);
        }
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId());
        rentAreaRepository.saveAllByBuilding(rentAreaEntities, Objects.nonNull(buildingEntity) ? buildingEntity.getRentAreaEntities() : new ArrayList<>());
    }
}
