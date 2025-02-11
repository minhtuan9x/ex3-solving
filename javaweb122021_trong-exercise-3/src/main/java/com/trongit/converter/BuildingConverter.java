package com.trongit.converter;


import com.trongit.dto.BuildingDTO;
import com.trongit.dto.request.BuildingSearchRequest;
import com.trongit.dto.response.BuildingResponse;
import com.trongit.entity.BuildingEntity;
import com.trongit.entity.RentAreaEntity;
import com.trongit.enums.DistrictEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingResponse toBuildingResponse(BuildingEntity buildingEntity) {
        BuildingResponse buildingResponse;
        buildingResponse = modelMapper.map(buildingEntity, BuildingResponse.class);
        String districtName = "";
        for (DistrictEnum item : DistrictEnum.values()) {
            if (item.name().equals(buildingEntity.getDistrict())) {
                districtName = item.getDistrictValue();
                break;
            }
        }
        buildingResponse.setAddress(buildingEntity.getStreet() + "-" + buildingEntity.getWard() + "-" + districtName);
        return buildingResponse;
    }

    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<String> rentAreas = new ArrayList<>();
        for (RentAreaEntity item : buildingEntity.getRentAreaEntities()) {
            rentAreas.add(String.valueOf(item.getValue()));
        }
        String rentAreaStr = String.join(",", rentAreas);
        buildingDTO.setRentArea(rentAreaStr);
        if (buildingEntity.getType() != null) {
            List<String> typeDTOs = new ArrayList<>();
            String[] types = buildingEntity.getType().trim().split(",");
            for (String item : types) {
                typeDTOs.add(item);
            }
            buildingDTO.setType(typeDTOs);
        }
        return buildingDTO;
    }

    public BuildingSearchRequest toBuildingSearchRequest(BuildingSearchRequest buildingSearchRequest) {
        if (buildingSearchRequest.getRentTypes() != null) {
            List<String> a = new ArrayList<>();
            for (String item : buildingSearchRequest.getRentTypes()) {
                a.add("'" + item + "'");
            }
            buildingSearchRequest.setRentTypes(a);
        }
        return buildingSearchRequest;
    }

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        if (buildingDTO.getType() != null) {
            String type = String.join(",", buildingDTO.getType());
            buildingEntity.setType(type);
        }
        return buildingEntity;
    }
}
