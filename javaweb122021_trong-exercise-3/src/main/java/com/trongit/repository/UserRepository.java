package com.trongit.repository;

import com.trongit.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserNameAndStatus(String name, int status);

    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status, Pageable pageable);

    Page<UserEntity> findByStatusNot(int status, Pageable pageable);

    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status);

    long countByStatusNot(int status);

    UserEntity findOneByUserName(String userName);

    UserEntity findOneById(Long id);

    List<UserEntity> getAllByRoles_CodeAndStatus(String roleCode, Integer status);

    List<UserEntity> findAllByIdIn(List<Long> ids);

    List<UserEntity> findAllByBuildingEntities_IdAndRoles_CodeAndStatus(Long buildingId, String roleCode, Integer status);
}
