package com.enndfp.service;

import com.enndfp.entity.Building;

import java.util.List;

public interface BuildingService {
    List<Building> selectAll();

    List<Building> select(String key, String value);

    void save(Building building);

    void update(Building building);

    void  delete(Integer id);
}
