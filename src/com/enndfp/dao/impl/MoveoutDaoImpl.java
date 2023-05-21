package com.enndfp.dao.impl;

import com.enndfp.dao.MoveoutDao;
import com.enndfp.entity.Moveout;
import com.enndfp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveoutDaoImpl implements MoveoutDao {
    @Override
    public Integer save(Moveout moveout) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into moveout(student_id,dormitory_id,reason,create_date) values(?,?,?,?) ";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, moveout.getStudentId());
            statement.setInt(2, moveout.getDormitoryId());
            statement.setString(3, moveout.getReason());
            statement.setString(4, moveout.getCreateDate());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Moveout> list() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT m.id,s.`name` studentName,d.`name` dormitoryName,m.reason,m.create_date FROM moveout m,student s,dormitory d WHERE m.student_id=s.id AND m.dormitory_id=d.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Moveout> moveoutList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String studentName = resultSet.getString("studentName");
                String dormitoryName = resultSet.getString("dormitoryName");
                String reason = resultSet.getString("reason");
                String createDate = resultSet.getString("create_date");
                moveoutList.add(new Moveout(id,studentName,dormitoryName,reason,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return moveoutList;
    }

    @Override
    public List<Moveout> select(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT m.id,s.`name` studentName,d.`name` dormitoryName,m.reason,m.create_date FROM moveout m,student s,dormitory d WHERE m.student_id=s.id AND m.dormitory_id=d.id";
        String keyStatement="";
        if(key.equals("studentName")){
            keyStatement=" and s.`name`";
        }
        if(key.equals("dormitoryName")){
            keyStatement=" and d.`name`";
        }
        sql= sql +keyStatement +"like '%"+value+"%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Moveout> moveoutList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String studentName = resultSet.getString("studentName");
                String dormitoryName = resultSet.getString("dormitoryName");
                String reason = resultSet.getString("reason");
                String createDate = resultSet.getString("create_date");
                moveoutList.add(new Moveout(id,studentName,dormitoryName,reason,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return moveoutList;
    }


}
