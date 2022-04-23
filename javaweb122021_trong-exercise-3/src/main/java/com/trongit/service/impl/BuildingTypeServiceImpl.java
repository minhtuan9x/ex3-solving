package com.trongit.service.impl;

import com.trongit.dto.BuildingDTO;
import com.trongit.dto.response.BuildingTypeResponse;
import com.trongit.enums.BuildingTypeEnum;
import com.trongit.enums.DistrictEnum;
import com.trongit.service.BuildingTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingTypeServiceImpl implements BuildingTypeService {
    @Override
    public List<BuildingTypeResponse> getAll() {
        List<BuildingTypeResponse> buildingTypeDTOS = new ArrayList<>();
        for (BuildingTypeEnum item : BuildingTypeEnum.values()) {
            BuildingTypeResponse buildingTypeResponse = new BuildingTypeResponse();
            buildingTypeResponse.setCode(item.name());
            buildingTypeResponse.setName(item.getBuildingTypeValue());
            buildingTypeDTOS.add(buildingTypeResponse);
        }
        return buildingTypeDTOS;
    }

    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String, String> districtMap = new HashMap<>();
        for (BuildingTypeEnum item : BuildingTypeEnum.values()) {
            districtMap.put(item.name(), item.getBuildingTypeValue());
        }
        return districtMap;
    }
}
