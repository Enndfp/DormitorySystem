package com.enndfp.service;

import com.enndfp.dto.DormitoryAdminDto;
import com.enndfp.entity.DormitoryAdmin;

import java.util.List;

public interface DormitoryAdminService {
    List<DormitoryAdmin> selectAll();

    List<DormitoryAdmin> select(String key, String value);

    void save(DormitoryAdmin dormitoryAdmin);

    void update(DormitoryAdmin dormitoryAdmin);

    void delete(Integer id);

    DormitoryAdminDto login(String username, String password);

}
