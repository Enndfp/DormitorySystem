package com.enndfp.dao;

import com.enndfp.entity.Moveout;

import java.util.List;

public interface MoveoutDao {
    Integer save(Moveout moveout);

    List<Moveout> list();

    List<Moveout> select(String key,String value);
}
