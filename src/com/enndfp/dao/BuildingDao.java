package com.enndfp.dao;

import com.enndfp.entity.Building;

import java.util.List;

public interface BuildingDao {
    List<Building> selectAll();

    List<Building> select(String key, String value);

    Integer save(Building building);

    Integer update(Building building);

    Integer delete(Integer id);

}
