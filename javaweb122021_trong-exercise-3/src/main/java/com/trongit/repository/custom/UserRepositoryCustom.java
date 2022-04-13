package com.trongit.repository.custom;

import com.trongit.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> getAllStaff();
    List<UserEntity> getAllStaffByBuildingID(Long buildingid);
}
