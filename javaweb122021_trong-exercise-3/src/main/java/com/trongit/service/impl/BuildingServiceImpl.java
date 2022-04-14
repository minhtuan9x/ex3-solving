package com.trongit.service.impl;

import com.trongit.builder.BuildingSearchBuilder;
import com.trongit.constant.SystemConstant;
import com.trongit.converter.BuildingConverter;
import com.trongit.converter.RentAreaConverter;
import com.trongit.dto.BuildingDTO;
import com.trongit.dto.RentAreaDTO;
import com.trongit.dto.request.AssignmentBuildingRequest;
import com.trongit.dto.request.BuildingSearchRequest;
import com.trongit.dto.response.BuildingResponse;
import com.trongit.entity.BuildingEntity;
import com.trongit.entity.UserEntity;
import com.trongit.repository.BuildingRepository;
import com.trongit.repository.UserRepository;
import com.trongit.security.utils.SecurityUtils;
import com.trongit.service.BuildingService;
import com.trongit.service.RentAreaService;
import com.trongit.utils.MapUtil;
import com.trongit.utils.ParseIntUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BuildingResponse> findAll(Map<String, Object> params, List<String> rentTypes) {

        List<BuildingResponse> buildingResponses = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = toBuildingSearchBuilder(params, rentTypes);
        for (BuildingEntity item : buildingRepository.findAll(buildingSearchBuilder)) {
            buildingResponses.add(buildingConverter.toBuildingResponse(item));
        }
        return buildingResponses;
    }

    @Override
    public List<BuildingResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        try {
            if (SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE))
                buildingSearchRequest.setStaffID(SecurityUtils.getPrincipal().getId());
            return buildingRepository.findAll(toBuildingSearchBuilder(buildingSearchRequest)).stream().map(buildingConverter::toBuildingResponse).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        return new BuildingSearchBuilder.Builder()
                .name(buildingSearchRequest.getName())
                .direction(buildingSearchRequest.getDirection())
                .district(buildingSearchRequest.getDistrict())
                .floorArea(buildingSearchRequest.getFloorArea())
                .level(buildingSearchRequest.getLevel())
                .managerName(buildingSearchRequest.getManagerName())
                .managerPhone(buildingSearchRequest.getManagerPhone())
                .numberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .rentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                .rentAreaTo(buildingSearchRequest.getRentAreaTo())
                .rentPriceFrom(buildingSearchRequest.getRentPriceFrom())
                .rentPriceTo(buildingSearchRequest.getRentPriceTo())
                .rentTypes(buildingSearchRequest.getRentTypes())
                .staffID(Objects.nonNull(buildingSearchRequest.getStaffID()) ? buildingSearchRequest.getStaffID().intValue() : null)
                .street(buildingSearchRequest.getStreet())
                .ward(buildingSearchRequest.getWard())
                .build();
    }

    @Override
    public List<BuildingResponse> findByNameLike(String name) {
        List<BuildingResponse> buildingResponses = new ArrayList<>();
        for (BuildingEntity item : buildingRepository.findByNameContaining(name)) {
            buildingResponses.add(buildingConverter.toBuildingResponse(item));
        }
        return buildingResponses;
    }

    @Override
    public BuildingDTO findById(Long id) {
        return id != null ? buildingConverter.toBuildingDTO(buildingRepository.findById(id)) : new BuildingDTO();
    }

    @Override
    public BuildingDTO save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        try {
            BuildingEntity buildingEntityRes = buildingRepository.save(buildingEntity);
            List<RentAreaDTO> rentAreaDTOS = rentAreaConverter.toRentAreaDTOs(buildingEntityRes.getId(), buildingDTO);
            rentAreaService.saveAllByBuilding(rentAreaDTOS, buildingDTO);
            return buildingConverter.toBuildingDTO(buildingEntityRes);
        } catch (Exception e) {
            System.out.println("Error BuildingService");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void assignmentBuilding(AssignmentBuildingRequest assignmentBuildingRequest, Long buildingID) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (Integer item : assignmentBuildingRequest.getStaffIDs()) {
            userEntities.add(userRepository.findOneById(item.longValue()));
        }
        BuildingEntity buildingEntity = buildingRepository.findById(buildingID);
        buildingRepository.assignmentBuilding(userEntities, buildingEntity);
    }

    @Override
    public void delete(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id);
        if (buildingEntity != null) {
            buildingRepository.deleteBuilding(buildingEntity);
        }
    }

    private BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> rentTypes) {
        try {
            Map<String, Object> paramsPsd = toLowKey(params);
            BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                    .name((String) MapUtil.getValue(paramsPsd, "name"))
                    .floorArea(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "floorarea")))
                    .district((String) MapUtil.getValue(paramsPsd, "districtcode"))
                    .ward((String) MapUtil.getValue(paramsPsd, "ward"))
                    .street((String) MapUtil.getValue(paramsPsd, "street"))
                    .numberOfBasement(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "numberofbasement")))
                    .direction((String) MapUtil.getValue(paramsPsd, "direction"))
                    .level((String) MapUtil.getValue(paramsPsd, "level"))
                    .rentAreaFrom(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentareafrom")))
                    .rentAreaTo(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentareato")))
                    .rentPriceFrom(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentpricefrom")))
                    .rentPriceTo(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentpriceto")))
                    .managerName((String) MapUtil.getValue(paramsPsd, "managername"))
                    .managerPhone((String) MapUtil.getValue(paramsPsd, "managerphone"))
                    .staffID(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "staffid"))).rentTypes(rentTypes)
                    .build();
            return buildingSearchBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, Object> toLowKey(Map<String, Object> params) {
        Map<String, Object> results = new HashMap<>();
        for (Map.Entry<String, Object> item : params.entrySet()) {
            results.put(item.getKey().toLowerCase(), item.getValue());
        }
        return results;
    }


}
