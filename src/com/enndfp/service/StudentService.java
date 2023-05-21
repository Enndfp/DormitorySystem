package com.enndfp.service;

import com.enndfp.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> selectAll();

    List<Student> select(String key, String value);

    void save(Student student);

    void update(Student student,Integer oldDormitoryId);

    void delete(Integer id,Integer dormitoryId);

    List<Student> moveoutList();

    List<Student> selectMoveout(String key, String value);

    List<Student> findByDormitoryId(Integer dormitoryId);
}
