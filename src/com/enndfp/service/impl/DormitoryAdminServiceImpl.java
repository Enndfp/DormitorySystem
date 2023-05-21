package com.enndfp.service.impl;

import com.enndfp.dao.DormitoryAdminDao;
import com.enndfp.dao.impl.DormitoryAdminDaoImpl;
import com.enndfp.dto.DormitoryAdminDto;
import com.enndfp.entity.DormitoryAdmin;
import com.enndfp.service.DormitoryAdminService;

import java.util.List;

public class DormitoryAdminServiceImpl implements DormitoryAdminService {
    private DormitoryAdminDao dormitoryAdminDao = new DormitoryAdminDaoImpl();

    @Override
    public List<DormitoryAdmin> selectAll() {
        return this.dormitoryAdminDao.selectAll();
    }

    @Override
    public List<DormitoryAdmin> select(String key, String value) {
        if (value.equals("")) return this.dormitoryAdminDao.selectAll();
        return this.dormitoryAdminDao.select(key, value);
    }

    @Override
    public void save(DormitoryAdmin dormitoryAdmin) {
        Integer save = this.dormitoryAdminDao.save(dormitoryAdmin);
        if (save != 1) throw new RuntimeException("宿管信息添加失败");
    }

    @Override
    public void update(DormitoryAdmin dormitoryAdmin) {
        Integer update = this.dormitoryAdminDao.update(dormitoryAdmin);
        if (update != 1) throw new RuntimeException("宿管信息修改失败");
    }

    @Override
    public void delete(Integer id) {
        Integer delete = this.dormitoryAdminDao.delete(id);
        if (delete != 1) throw new RuntimeException("宿管信息删除失败");
    }

    @Override
    public DormitoryAdminDto login(String username, String password) {
        //1.通过username查询数据库
        //2.查询结果为空，username错误
        //3.查询结果不为空，再判断password是否正确
        DormitoryAdmin dormitoryAdmin = this.dormitoryAdminDao.selectByUsername(username);
        DormitoryAdminDto dormitoryAdminDto = new DormitoryAdminDto();
        if (dormitoryAdmin == null) {
            dormitoryAdminDto.setCode(-1);
        } else {
            if (!dormitoryAdmin.getPassword().equals(password)) {
                dormitoryAdminDto.setCode(-2);
            } else {
                dormitoryAdminDto.setCode(0);
                dormitoryAdminDto.setDormitoryAdmin(dormitoryAdmin);
            }
        }
        return dormitoryAdminDto;
    }
}
