package com.trongit.converter;

import com.trongit.dto.BuildingDTO;
import com.trongit.dto.RentAreaDTO;
import com.trongit.entity.RentAreaEntity;
import com.trongit.repository.BuildingRepository;
import com.trongit.repository.RentAreaRepository;
import com.trongit.utils.ParseIntUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RentAreaConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private BuildingConverter buildingConverter;

    public RentAreaEntity toRentAreaEntity(RentAreaDTO rentAreaDTO) {
        RentAreaEntity rentAreaEntity = modelMapper.map(rentAreaDTO, RentAreaEntity.class);
        rentAreaEntity.setBuildingEntity(buildingRepository.findById(rentAreaDTO.getBuildingID()));
        return rentAreaEntity;
    }

    public List<RentAreaDTO> toRentAreaDTOs(Long buildingID, BuildingDTO buildingDTO) {
        List<RentAreaDTO> rentAreaDTOS = new ArrayList<>();
        List<String> rentArea = buildingDTO.getRentArea() != null ? Arrays.stream(buildingDTO.getRentArea().trim().split(","))
                .filter(item->item.matches("[0-9]+")).collect(Collectors.toList()) : null;
        if (rentArea != null) {
            for (String item : rentArea) {
                RentAreaDTO rentAreaDTO = new RentAreaDTO();
                rentAreaDTO.setBuildingID(buildingID);
                rentAreaDTO.setValue(ParseIntUtil.getValue(item));
                rentAreaDTOS.add(rentAreaDTO);
            }
            return rentAreaDTOS;
        } else
            return new ArrayList<>();
    }
}
