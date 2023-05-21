package com.enndfp.service.impl;

import com.enndfp.dao.AbsentDao;
import com.enndfp.dao.impl.AbsentDaoImpl;
import com.enndfp.entity.Absent;
import com.enndfp.service.AbsentService;

import java.util.List;

public class AbsentServiceImpl implements AbsentService {
    private AbsentDao absentDao = new AbsentDaoImpl();

    @Override
    public void save(Absent absent) {
        Integer save = this.absentDao.save(absent);
        if (save != 1) throw new RuntimeException("添加缺寝记录失败");
    }

    @Override
    public List<Absent> selectAll() {
        return this.absentDao.selectAll();
    }

    @Override
    public List<Absent> select(String key, String value) {
        if (value.equals("")) return this.absentDao.selectAll();
        return this.absentDao.select(key, value);
    }
}
