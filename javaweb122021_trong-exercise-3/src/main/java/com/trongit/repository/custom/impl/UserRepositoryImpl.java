package com.trongit.repository.custom.impl;

import com.trongit.entity.UserEntity;
import com.trongit.repository.custom.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserEntity> getAllStaffByBuildingID(Long buildingid) {
        StringBuilder sql =new StringBuilder("Select * from user as u inner join assignmentbuilding as ab on u.id = staffid ");
        sql.append("where ab.buildingid = ").append(buildingid).append(" and u.status = 1");
        Query query = entityManager.createNativeQuery(sql.toString(),UserEntity.class);
        return query.getResultList();
    }
}
