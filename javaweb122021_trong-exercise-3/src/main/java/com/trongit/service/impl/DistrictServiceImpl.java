package com.trongit.service.impl;

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
    public Map<String, String> getDistricts() {
        Map<String, String> districtMap = new HashMap<>();
        for (DistrictEnum item : DistrictEnum.values()) {
            districtMap.put(item.name(), item.getDistrictValue());
        }
        return districtMap;
    }
}
