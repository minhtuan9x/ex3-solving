package com.trongit.repository.custom.impl;

import com.trongit.builder.BuildingSearchBuilder;
import com.trongit.entity.BuildingEntity;
import com.trongit.repository.RentAreaRepository;
import com.trongit.repository.UserRepository;
import com.trongit.repository.custom.BuildingRepositoryCustom;
import com.trongit.utils.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

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
}
