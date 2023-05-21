package com.enndfp.dao.impl;

import com.enndfp.dao.AbsentDao;
import com.enndfp.entity.Absent;
import com.enndfp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbsentDaoImpl implements AbsentDao {
    @Override
    public Integer save(Absent absent) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into absent(building_id,dormitory_id,student_id,dormitory_admin_id,create_date,reason) values(?,?,?,?,?,?) ";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, absent.getBuildingId());
            statement.setInt(2, absent.getDormitoryId());
            statement.setInt(3, absent.getStudentId());
            statement.setInt(4, absent.getDormitoryAdminId());
            statement.setString(5, absent.getCreateDate());
            statement.setString(6, absent.getReason());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Absent> selectAll() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT a.id,b.`name`,d.`name`,s.`name`,a.reason,da.`name`,a.create_date FROM absent a,building b,dormitory d,student s,dormitory_admin da WHERE b.id=a.building_id AND d.id=a.dormitory_id AND s.id=a.student_id AND da.id=a.dormitory_admin_id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Absent> absentList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String buildingName = resultSet.getString(2);
                String dormitoryName = resultSet.getString(3);
                String studentName = resultSet.getString(4);
                String reason = resultSet.getString(5);
                String dormitoryAdminName = resultSet.getString(6);
                String createDate = resultSet.getString(7);
                absentList.add(new Absent(id,buildingName,dormitoryName,studentName,reason,dormitoryAdminName,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return absentList;
    }

    @Override
    public List<Absent> select(String key,String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT a.id,b.`name`,d.`name`,s.`name`,a.reason,da.`name`,a.create_date FROM absent a,building b,dormitory d,student s,dormitory_admin da WHERE b.id=a.building_id AND d.id=a.dormitory_id AND s.id=a.student_id AND da.id=a.dormitory_admin_id";
        String keyStatement="";
        if(key.equals("buildingName")){
            keyStatement=" and b.`name`";
        }
        if(key.equals("dormitoryName")){
            keyStatement=" and d.`name`";
        }
        sql= sql +keyStatement +"like '%"+value+"%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Absent> absentList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String buildingName = resultSet.getString(2);
                String dormitoryName = resultSet.getString(3);
                String studentName = resultSet.getString(4);
                String reason = resultSet.getString(5);
                String dormitoryAdminName = resultSet.getString(6);
                String createDate = resultSet.getString(7);
                absentList.add(new Absent(id,buildingName,dormitoryName,studentName,reason,dormitoryAdminName,createDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return absentList;
    }
}
