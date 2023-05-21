package com.enndfp.service;

import com.enndfp.dto.SystemAdminDto;

public interface SystemAdminService {
    SystemAdminDto login(String username, String password);
}
