package com.trongit.service.impl;

import com.trongit.dto.BuildingDTO;
import com.trongit.dto.response.DistrictResponse;
import com.trongit.enums.DistrictEnum;
import com.trongit.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Override
    public List<DistrictResponse> getAll() {
        List<DistrictResponse> districtResponses = new ArrayList<>();
        for (DistrictEnum item : DistrictEnum.values()) {
            DistrictResponse districtResponse = new DistrictResponse();
            districtResponse.setCode(item.name());
            districtResponse.setName(item.getDistrictValue());
            districtResponses.add(districtResponse);
        }
        return districtResponses;
    }

    @Override
    public List<DistrictResponse> getAllByBuilding(BuildingDTO buildingDTO) {
        List<DistrictResponse> districtResponses = new ArrayList<>();
        try {
            for (DistrictEnum item : DistrictEnum.values()) {
                DistrictResponse districtResponse = new DistrictResponse();
                districtResponse.setCode(item.name());
                districtResponse.setName(item.getDistrictValue());
                if (buildingDTO.getDistrict() != null && buildingDTO.getDistrict().equals(item.name()))
                    districtResponse.setSelected("selected");
                districtResponses.add(districtResponse);
            }
            return districtResponses;
        } catch (Exception e) {
            System.out.println("Loi District Service");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Map<String, String> getDistricts() {
        Map<String, String> districtMap = new HashMap<>();
        for (DistrictEnum item : DistrictEnum.values()) {
            districtMap.put(item.name(), item.getDistrictValue());
        }
        return districtMap;
    }
}
