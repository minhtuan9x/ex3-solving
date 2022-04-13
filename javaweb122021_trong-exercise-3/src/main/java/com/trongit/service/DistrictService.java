package com.trongit.service;

import com.trongit.dto.BuildingDTO;
import com.trongit.dto.response.DistrictResponse;

import java.util.List;
import java.util.Map;

public interface DistrictService {
    List<DistrictResponse> getAll();
    List<DistrictResponse> getAllByBuilding(BuildingDTO buildingDTO);
    Map<String,String> findAllWithMap();
}
