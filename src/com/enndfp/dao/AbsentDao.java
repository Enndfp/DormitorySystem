package com.enndfp.dao;

import com.enndfp.entity.Absent;

import java.util.List;

public interface AbsentDao {
    Integer save(Absent absent);

    List<Absent> selectAll();

    List<Absent> select(String key,String value);
}
