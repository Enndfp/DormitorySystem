package com.enndfp.dao.impl;

import com.enndfp.dao.StudentDao;
import com.enndfp.entity.Student;
import com.enndfp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> selectAll() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT s.id,s.number,s.`name`,s.gender,s.dormitory_id dormitoryId,d.`name` dormitoryName,s.state,s.create_date createDate FROM student s,dormitory d WHERE s.dormitory_id=d.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                Integer dormitoryId = resultSet.getInt("dormitoryId");
                String dormitoryName = resultSet.getString("dormitoryName");
                String state = resultSet.getString("state");
                String createDate = resultSet.getString("createDate");
                studentList.add(new Student(id, number, name, gender, dormitoryId, dormitoryName, state, createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return studentList;
    }

    @Override
    public List<Student> select(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT s.id,s.number,s.`name`,s.gender,s.dormitory_id dormitoryId,d.`name` dormitoryName,s.state,s.create_date createDate FROM student s,dormitory d WHERE s.dormitory_id=d.id AND s." + key + " LIKE '%" + value + "%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                Integer dormitoryId = resultSet.getInt("dormitoryId");
                String dormitoryName = resultSet.getString("dormitoryName");
                String state = resultSet.getString("state");
                String createDate = resultSet.getString("createDate");
                studentList.add(new Student(id, number, name, gender, dormitoryId, dormitoryName, state, createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return studentList;
    }

    @Override
    public Integer save(Student student) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into student(number,name,gender,dormitory_id,state,create_date) values(?,?,?,?,?,?) ";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getNumber());
            statement.setString(2, student.getName());
            statement.setString(3, student.getGender());
            statement.setInt(4, student.getDormitoryId());
            statement.setString(5, student.getState());
            statement.setString(6, student.getCreateDate());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public Integer update(Student student) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update student set number=?,name=?,gender=?,dormitory_id=? where id=?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getNumber());
            statement.setString(2, student.getName());
            statement.setString(3, student.getGender());
            statement.setInt(4, student.getDormitoryId());
            statement.setInt(5, student.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public Integer delete(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "delete from student where id=" + id;
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Integer> findStudentIdByDormitoryId(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id from student where dormitory_id=" + id;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> studentIdList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                studentIdList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return studentIdList;
    }

    @Override
    public Integer updateDormitory(Integer studentId, Integer dormitoryId) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update student set dormitory_id=? where id=?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dormitoryId);
            statement.setInt(2, studentId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Student> moveoutList() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT s.id,s.number,s.`name`,s.gender,s.dormitory_id dormitoryId,d.`name` dormitoryName,s.state FROM student s,dormitory d WHERE s.dormitory_id=d.id and s.state='入住'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                Integer dormitoryId = resultSet.getInt("dormitoryId");
                String dormitoryName = resultSet.getString("dormitoryName");
                String state = resultSet.getString("state");
                studentList.add(new Student(id, number, name, gender, dormitoryId, dormitoryName, state));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return studentList;
    }

    @Override
    public List<Student> selectMoveout(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT s.id,s.number,s.`name`,s.gender,s.dormitory_id dormitoryId,d.`name` dormitoryName,s.state FROM student s,dormitory d WHERE s.dormitory_id=d.id and s.state='入住' AND s." + key + " LIKE '%" + value + "%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String number = resultSet.getString("number");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                Integer dormitoryId = resultSet.getInt("dormitoryId");
                String dormitoryName = resultSet.getString("dormitoryName");
                String state = resultSet.getString("state");
                studentList.add(new Student(id, number, name, gender, dormitoryId, dormitoryName, state));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return studentList;
    }

    @Override
    public Integer updataStateById(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update student set state='迁出' where id=" + id;
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Student> findByDormitoryId(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id,name from student where dormitory_id=? and state='入住'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Student> studentList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                 id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                studentList.add(new Student(id,name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return studentList;
    }
}
