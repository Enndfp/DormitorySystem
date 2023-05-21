package com.enndfp.service.impl;

import com.enndfp.dao.SystemAdminDao;
import com.enndfp.dao.impl.SystemAdminDaoImpl;
import com.enndfp.dto.SystemAdminDto;
import com.enndfp.entity.SystemAdmin;
import com.enndfp.service.SystemAdminService;

public class SystemAdminServiceImpl implements SystemAdminService {
    private SystemAdminDao systemAdminDao = new SystemAdminDaoImpl();

    @Override
    public SystemAdminDto login(String username, String password) {
        //1.通过username查询数据库
        //2.查询结果为空，username错误
        //3.查询结果不为空，再判断password是否正确
        SystemAdmin systemAdmin = this.systemAdminDao.selectByUsername(username);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if (systemAdmin == null) {
            systemAdminDto.setCode(-1);
        } else {
            if (!systemAdmin.getPassword().equals(password)) {
                systemAdminDto.setCode(-2);
            } else {
                systemAdminDto.setCode(0);
                systemAdminDto.setSystemAdmin(systemAdmin);
            }
        }
        return systemAdminDto;
    }
}
