package com.enndfp.dao;

import com.enndfp.entity.DormitoryAdmin;

import java.util.List;

public interface DormitoryAdminDao {
    List<DormitoryAdmin> selectAll();

    List<DormitoryAdmin> select(String key, String value);

    Integer save(DormitoryAdmin dormitoryAdmin);

    Integer update(DormitoryAdmin dormitoryAdmin);

    Integer delete(Integer id);

    DormitoryAdmin selectByUsername(String username);
}
