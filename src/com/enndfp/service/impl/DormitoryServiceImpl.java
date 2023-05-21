package com.enndfp.service.impl;

import com.enndfp.dao.DormitoryDao;
import com.enndfp.dao.StudentDao;
import com.enndfp.dao.impl.DormitoryDaoImpl;
import com.enndfp.dao.impl.StudentDaoImpl;
import com.enndfp.entity.Dormitory;
import com.enndfp.service.DormitoryService;

import java.util.List;

public class DormitoryServiceImpl implements DormitoryService {
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Dormitory> availableList() {
        return this.dormitoryDao.availableList();
    }

    @Override
    public List<Dormitory> selectAll() {
        return this.dormitoryDao.selectAll();
    }

    @Override
    public List<Dormitory> select(String key, String value) {
        if (value.equals("")) return this.dormitoryDao.selectAll();
        return this.dormitoryDao.select(key, value);
    }

    @Override
    public void save(Dormitory dormitory) {
        Integer save = this.dormitoryDao.save(dormitory);
        if (save != 1) throw new RuntimeException("添加宿舍信息失败");
    }

    @Override
    public void update(Dormitory dormitory) {
        Integer update = this.dormitoryDao.update(dormitory);
        if (update != 1) throw new RuntimeException("修改宿舍信息失败");
    }

    @Override
    public void delete(Integer id) {
        //根据宿舍id查找学生id
        List<Integer> studentIdList = this.studentDao.findStudentIdByDormitoryId(id);
        for (Integer studentId : studentIdList) {
            //查找有空余床位的宿舍id
            Integer availableId = this.dormitoryDao.availableId();
            //修改学生宿舍
            Integer updateDormitory = this.studentDao.updateDormitory(studentId, availableId);
            //将有空余床位的那个宿舍床位-1
            Integer updateAvailable = this.dormitoryDao.updateAvailable(availableId);
            if (updateDormitory != 1 || updateAvailable != 1) throw new RuntimeException("学生更换宿舍失败");
        }
        Integer delete = this.dormitoryDao.deleteById(id);
        if (delete != 1) throw new RuntimeException("删除宿舍信息失败");
    }

    @Override
    public List<Dormitory> findByBuildingId(Integer buildingId) {
        return this.dormitoryDao.findByBuildingId(buildingId);
    }

}
