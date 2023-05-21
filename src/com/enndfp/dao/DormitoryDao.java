package com.enndfp.dao;

import com.enndfp.entity.Dormitory;

import java.util.List;

public interface DormitoryDao {
    List<Dormitory> selectAll();

    List<Dormitory> select(String key,String value);

    Integer save (Dormitory dormitory);

    Integer update(Dormitory dormitory);

    List<Dormitory> availableList();

    Integer updateAvailable(Integer id);

    Integer addAvailable(Integer id);

    List<Integer> findDormitoryIdByBuildingId(Integer id);

    Integer availableId();

    Integer deleteById(Integer id);

    List<Dormitory> findByBuildingId(Integer id);
}
