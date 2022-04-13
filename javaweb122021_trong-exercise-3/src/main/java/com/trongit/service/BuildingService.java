package com.trongit.service;

import com.trongit.dto.BuildingDTO;
import com.trongit.dto.request.AssignmentBuildingRequest;
import com.trongit.dto.request.BuildingSearchRequest;
import com.trongit.dto.response.BuildingResponse;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingResponse> findAll(Map<String, Object> params, List<String> rentTypes);
    List<BuildingResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    List<BuildingResponse> findByNameLike(String name);
    BuildingDTO findById(Long id);
    BuildingDTO save(BuildingDTO buildingDTO);
    void assignmentBuilding(AssignmentBuildingRequest assignmentBuildingRequest, Long buildingID);
    void delete(Long id);
}
