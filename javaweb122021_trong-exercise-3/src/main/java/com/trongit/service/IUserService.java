package com.trongit.service;

import com.trongit.dto.PasswordDTO;
import com.trongit.dto.UserDTO;
import com.trongit.dto.response.StaffAssignmentResponse;
import com.trongit.exception.MyException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    UserDTO findOneByUserNameAndStatus(String name, int status);
    List<UserDTO> getUsers(String searchValue, Pageable pageable);
    int getTotalItems(String searchValue);
    UserDTO findOneByUserName(String userName);
    UserDTO findUserById(long id);
    UserDTO insert(UserDTO userDTO);
    UserDTO update(Long id, UserDTO userDTO);
    void updatePassword(long id, PasswordDTO userDTO) throws MyException;
    UserDTO resetPassword(long id);
    UserDTO updateProfileOfUser(String id, UserDTO userDTO);
    void delete(long[] ids);
    List<UserDTO> getAllStaff();
    List<StaffAssignmentResponse> getAllStaffAssignmentBuilding(Long buildingID);
}
