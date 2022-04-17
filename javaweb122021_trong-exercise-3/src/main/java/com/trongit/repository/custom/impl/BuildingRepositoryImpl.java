package com.trongit.repository.custom.impl;

import com.trongit.builder.BuildingSearchBuilder;
import com.trongit.entity.*;
import com.trongit.repository.AssignmentBuildingRepository;
import com.trongit.repository.RentAreaRepository;
import com.trongit.repository.UserRepository;
import com.trongit.repository.custom.BuildingRepositoryCustom;
import com.trongit.utils.SqlUtil;
import com.trongit.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        try {
            String sql = SqlUtil.buildQuery(buildingSearchBuilder);
            Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Transactional
    @Override
    public void assignmentBuilding(List<UserEntity> userEntities, BuildingEntity buildingEntity) {
        for (UserEntity item : userRepository.getAllStaffByBuildingID(buildingEntity.getId())) {
            int flag = 0;
            for (UserEntity item2 : userEntities) {
                if (item.getId() == item2.getId())
                    flag++;
            }
            if (flag == 0) {
                AssignmentBuildingEntity assignmentBuildingEntity
                        = assignmentBuildingRepository.findByBuildingEntityAndUserEntity(buildingEntity, item);
                entityManager.remove(assignmentBuildingEntity);
            }

        }
        for (UserEntity item : userEntities) {
            int flag = 0;
            for (UserEntity item2 : userRepository.getAllStaffByBuildingID(buildingEntity.getId())) {
                if (item.getId() == item2.getId())
                    flag++;
            }
            if (flag == 0) {

                AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
                assignmentBuildingEntity.setBuildingEntity(buildingEntity);
                assignmentBuildingEntity.setUserEntity(item);
                entityManager.persist(assignmentBuildingEntity);
            }
        }
    }


}
