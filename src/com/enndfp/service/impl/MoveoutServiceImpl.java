package com.enndfp.service.impl;

import com.enndfp.dao.DormitoryDao;
import com.enndfp.dao.MoveoutDao;
import com.enndfp.dao.StudentDao;
import com.enndfp.dao.impl.DormitoryDaoImpl;
import com.enndfp.dao.impl.MoveoutDaoImpl;
import com.enndfp.dao.impl.StudentDaoImpl;
import com.enndfp.entity.Moveout;
import com.enndfp.service.MoveoutService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoveoutServiceImpl implements MoveoutService {
    private MoveoutDao moveoutDao = new MoveoutDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();

    @Override
    public void save(Moveout moveout) {
        Integer updataStateById = this.studentDao.updataStateById(moveout.getStudentId());
        Integer addAvailable = this.dormitoryDao.addAvailable(moveout.getDormitoryId());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        moveout.setCreateDate(simpleDateFormat.format(date));
        Integer save = this.moveoutDao.save(moveout);
        if (save != 1 || updataStateById != 1 || addAvailable != 1) throw new RuntimeException("迁出学生失败");
    }

    @Override
    public List<Moveout> list() {
        return this.moveoutDao.list();
    }

    @Override
    public List<Moveout> select(String key, String value) {
        if (value.equals("")) return this.moveoutDao.list();
        return this.moveoutDao.select(key, value);
    }
}
