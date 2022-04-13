package com.trongit.service;

import com.trongit.dto.BuildingDTO;
import com.trongit.dto.response.BuildingTypeResponse;

import java.util.List;
import java.util.Map;

public interface BuildingTypeService {
    List<BuildingTypeResponse> getAll();
    List<BuildingTypeResponse> getAllByBuilding(BuildingDTO buildingDTO);
    Map<String,String> findAllWithMap();
}
