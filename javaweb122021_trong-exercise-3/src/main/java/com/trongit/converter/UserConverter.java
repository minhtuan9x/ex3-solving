package com.trongit.converter;

import com.trongit.dto.UserDTO;
import com.trongit.dto.response.StaffAssignmentResponse;
import com.trongit.entity.UserEntity;
import com.trongit.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public UserDTO convertToDto(UserEntity entity) {
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity(UserDTO dto) {
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }

    public List<StaffAssignmentResponse> toStaffAssignmentResponses(List<UserEntity> staffAssignments) {
        List<StaffAssignmentResponse> staffAssignmentResponses = new ArrayList<>();
        for (UserEntity item : userRepository.getAllByRoles_CodeAndStatus("STAFF",1)) {
            int flag = 0;
            for (UserEntity item2 : staffAssignments) {
                    if(Objects.equals(item.getId(), item2.getId()))
                        flag++;
            }
            StaffAssignmentResponse staffAssignmentResponse = modelMapper.map(item,StaffAssignmentResponse.class);
            if(flag>0)
                staffAssignmentResponse.setChecked("checked");
            staffAssignmentResponses.add(staffAssignmentResponse);
        }

        return staffAssignmentResponses;
    }
}
