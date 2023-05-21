package com.enndfp.service;

import com.enndfp.entity.Absent;

import java.util.List;

public interface AbsentService {
    void save(Absent absent);

    List<Absent> selectAll();

    List<Absent> select(String key,String value);
}
