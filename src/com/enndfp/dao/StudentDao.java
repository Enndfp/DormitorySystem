package com.enndfp.dao;

import com.enndfp.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectAll();

    List<Student> select(String key, String value);

    Integer save(Student student);

    Integer update(Student student);

    Integer delete(Integer id);

    List<Integer> findStudentIdByDormitoryId(Integer id);

    Integer updateDormitory(Integer studentId,Integer dormitoryId);

    List<Student> moveoutList();

    List<Student> selectMoveout(String key, String value);

    Integer updataStateById(Integer id);

    List<Student> findByDormitoryId(Integer id);
}
