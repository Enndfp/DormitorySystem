package com.enndfp.service.impl;

import com.enndfp.dao.DormitoryDao;
import com.enndfp.dao.StudentDao;
import com.enndfp.dao.impl.DormitoryDaoImpl;
import com.enndfp.dao.impl.StudentDaoImpl;
import com.enndfp.entity.Student;
import com.enndfp.service.StudentService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();
    private DormitoryDao dormitoryDao = new DormitoryDaoImpl();

    @Override
    public List<Student> selectAll() {
        return this.studentDao.selectAll();
    }

    @Override
    public List<Student> select(String key, String value) {
        if (value.equals("")) return this.studentDao.selectAll();
        return this.studentDao.select(key, value);
    }

    @Override
    public void save(Student student) {
        student.setState("入住");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        student.setCreateDate(simpleDateFormat.format(date));
        Integer save = this.studentDao.save(student);
        Integer available = this.dormitoryDao.updateAvailable(student.getDormitoryId());
        if (save != 1 || available != 1) throw new RuntimeException("添加学生信息失败");
    }

    @Override
    public void update(Student student, Integer oldDormitoryId) {
        Integer update = this.studentDao.update(student);
        //新宿舍床位-1
        Integer dormitory1 = this.dormitoryDao.updateAvailable(student.getDormitoryId());
        //原宿舍床位+1
        Integer dormitory2 = this.dormitoryDao.addAvailable(oldDormitoryId);
        if (update != 1 || dormitory1 != 1 || dormitory2 != 1) throw new RuntimeException("修改学生信息失败");
    }

    @Override
    public void delete(Integer id, Integer dormitoryId) {
        Integer delete = this.studentDao.delete(id);
        Integer available = this.dormitoryDao.addAvailable(dormitoryId);
        if (delete != 1 || available != 1) throw new RuntimeException("删除学生信息失败");
    }

    @Override
    public List<Student> moveoutList() {
        return this.studentDao.moveoutList();
    }

    @Override
    public List<Student> selectMoveout(String key, String value) {
        if (value.equals("")) return this.studentDao.selectMoveout(key, value);
        return this.studentDao.selectMoveout(key, value);
    }

    @Override
    public List<Student> findByDormitoryId(Integer dormitoryId) {
        return this.studentDao.findByDormitoryId(dormitoryId);
    }
}
