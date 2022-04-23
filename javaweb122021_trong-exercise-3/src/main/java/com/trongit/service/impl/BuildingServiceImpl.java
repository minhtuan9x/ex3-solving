package com.trongit.service.impl;

import com.trongit.builder.BuildingSearchBuilder;
import com.trongit.constant.SystemConstant;
import com.trongit.converter.BuildingConverter;
import com.trongit.converter.RentAreaConverter;
import com.trongit.dto.BuildingDTO;
import com.trongit.dto.request.AssignmentBuildingRequest;
import com.trongit.dto.request.BuildingSearchRequest;
import com.trongit.dto.response.BuildingResponse;
import com.trongit.entity.BuildingEntity;
import com.trongit.entity.UserEntity;
import com.trongit.repository.BuildingRepository;
import com.trongit.repository.RentAreaRepository;
import com.trongit.repository.UserRepository;
import com.trongit.security.utils.SecurityUtils;
import com.trongit.service.BuildingService;
import com.trongit.utils.MapUtil;
import com.trongit.utils.ParseIntUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;


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
    public BuildingDTO findById(Long id) {
        return id != null ? buildingConverter.toBuildingDTO(buildingRepository.findById(id)) : new BuildingDTO();
    }

    @Override
    public BuildingDTO save(BuildingDTO buildingDTO) {
        if (buildingDTO.getId() != null) {
            rentAreaRepository.deleteAllByBuildingEntity_IdIn(Collections.singletonList(buildingDTO.getId()));
        }
        BuildingEntity buildingEntityAfterSaved = buildingRepository.save(buildingConverter.toBuildingEntity(buildingDTO));
        rentAreaRepository.save(rentAreaConverter.toRentAreaEntities(buildingDTO.getRentArea(), buildingEntityAfterSaved.getId()));
        return buildingConverter.toBuildingDTO(buildingRepository.save(buildingEntityAfterSaved));
    }

    @Override
    public void assignmentBuilding(AssignmentBuildingRequest assignmentBuildingRequest, Long buildingID) {
        BuildingEntity buildingEntityFound = Optional.ofNullable(buildingRepository.findById(buildingID)).orElseThrow(() -> new RuntimeException("Not found Building"));
        List<UserEntity> userEntityFoundList = userRepository.findAllByIdIn(assignmentBuildingRequest.getStaffIDs());
        if (userEntityFoundList.size() != assignmentBuildingRequest.getStaffIDs().size())
            throw new RuntimeException("Not found Staff !!!");
        buildingEntityFound.setUserEntities(userEntityFoundList);
        buildingRepository.save(buildingEntityFound);
    }

    @Override
    public void deleteIn(List<Long> ids) {
        if (buildingRepository.findAll(ids).size() != ids.size())
            throw new RuntimeException("Not found building");
        buildingRepository.deleteAllByIdIn(ids);
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
