package com.trongit.service;

import com.trongit.dto.BuildingDTO;
import com.trongit.dto.RentAreaDTO;

import java.util.List;

public interface RentAreaService {
    void saveAllByBuilding(List<RentAreaDTO> rentAreaDTOS, BuildingDTO buildingDTO);
}
