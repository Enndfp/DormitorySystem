package com.enndfp.dao;

import com.enndfp.entity.SystemAdmin;

public interface SystemAdminDao {
    SystemAdmin selectByUsername(String username);
}
