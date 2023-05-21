package com.enndfp.service;

import com.enndfp.entity.Dormitory;

import java.util.List;

public interface DormitoryService {
    List<Dormitory> availableList();

    List<Dormitory> selectAll();

    List<Dormitory> select(String key,String value);

    void save(Dormitory dormitory);

    void update(Dormitory dormitory);

    void delete(Integer id);

    List<Dormitory> findByBuildingId(Integer buildingId);
}
