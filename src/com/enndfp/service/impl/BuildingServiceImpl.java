package com.enndfp.service.impl;

import com.enndfp.dao.BuildingDao;
import com.enndfp.dao.DormitoryDao;
import com.enndfp.dao.StudentDao;
import com.enndfp.dao.impl.BuildingDaoImpl;
import com.enndfp.dao.impl.DormitoryDaoImpl;
import com.enndfp.dao.impl.StudentDaoImpl;
import com.enndfp.entity.Building;
import com.enndfp.service.BuildingService;

import java.util.List;

public class BuildingServiceImpl implements BuildingService {
    private BuildingDao buildingDao = new BuildingDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Building> selectAll() {
        return this.buildingDao.selectAll();
    }

    @Override
    public List<Building> select(String key, String value) {
        if (value.equals("")) return this.buildingDao.selectAll();
        return this.buildingDao.select(key, value);
    }

    @Override
    public void save(Building building) {
        Integer save = this.buildingDao.save(building);
        if (save != 1) throw new RuntimeException("楼宇信息添加失败");
    }

    @Override
    public void update(Building building) {
        Integer update = this.buildingDao.update(building);
        if (update != 1) throw new RuntimeException("楼宇信息修改失败");
    }

    @Override
    public void delete(Integer id) {
        //1.学生换宿舍
        //根据楼宇id查找宿舍id
        List<Integer> dormitoryIdList = this.dormitoryDao.findDormitoryIdByBuildingId(id);
        for (Integer dormitoryId : dormitoryIdList) {
            //根据宿舍id查找学生id
            List<Integer> studentIdList = this.studentDao.findStudentIdByDormitoryId(dormitoryId);
            for (Integer studentId : studentIdList) {
                //查找有空余床位的宿舍id
                Integer availableId = this.dormitoryDao.availableId();
                //修改学生宿舍
                Integer updateDormitory = this.studentDao.updateDormitory(studentId, availableId);
                //将有空余床位的那个宿舍床位-1
                Integer updateAvailable = this.dormitoryDao.updateAvailable(availableId);
                if (updateDormitory != 1 || updateAvailable != 1) throw new RuntimeException("学生更换宿舍失败");
            }
        }
        //2.删除宿舍
        for (Integer dormitoryId : dormitoryIdList) {
            Integer delete = this.dormitoryDao.deleteById(dormitoryId);
            if(delete!=1) throw new RuntimeException("宿舍信息删除失败");
        }
        //3.删除楼宇
        Integer delete = this.buildingDao.delete(id);
        if(delete!=1) throw new RuntimeException("楼宇信息删除失败");
    }
}
