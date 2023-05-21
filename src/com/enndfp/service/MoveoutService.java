package com.enndfp.service;

import com.enndfp.entity.Moveout;

import java.util.List;

public interface MoveoutService {
    void save(Moveout moveout);

    List<Moveout> list();

    List<Moveout> select(String key,String value);
}
