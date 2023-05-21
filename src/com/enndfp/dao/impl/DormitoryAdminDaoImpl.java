package com.enndfp.dao.impl;

import com.enndfp.dao.DormitoryAdminDao;
import com.enndfp.entity.DormitoryAdmin;
import com.enndfp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryAdminDaoImpl implements DormitoryAdminDao {
    @Override
    public List<DormitoryAdmin> selectAll() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from dormitory_admin";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DormitoryAdmin> dormitoryAdminList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String telephone = resultSet.getString("telephone");
                dormitoryAdminList.add(new DormitoryAdmin(id, username, password, name, gender, telephone));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return dormitoryAdminList;
    }

    @Override
    public List<DormitoryAdmin> select(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from dormitory_admin where " + key + " like '%" + value + "%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<DormitoryAdmin> dormitoryAdminList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String telephone = resultSet.getString("telephone");
                dormitoryAdminList.add(new DormitoryAdmin(id, username, password, name, gender, telephone));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return dormitoryAdminList;
    }

    @Override
    public Integer save(DormitoryAdmin dormitoryAdmin) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into dormitory_admin(username,password,name,gender,telephone) values(?,?,?,?,?) ";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitoryAdmin.getUsername());
            statement.setString(2, dormitoryAdmin.getPassword());
            statement.setString(3, dormitoryAdmin.getName());
            statement.setString(4, dormitoryAdmin.getGender());
            statement.setString(5, dormitoryAdmin.getTelephone());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public Integer update(DormitoryAdmin dormitoryAdmin) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update dormitory_admin set username=?,password=?,name=?,gender=?,telephone=? where id=?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitoryAdmin.getUsername());
            statement.setString(2, dormitoryAdmin.getPassword());
            statement.setString(3, dormitoryAdmin.getName());
            statement.setString(4, dormitoryAdmin.getGender());
            statement.setString(5, dormitoryAdmin.getTelephone());
            statement.setInt(6, dormitoryAdmin.getId());
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
        String sql = "delete from dormitory_admin where id=" + id;
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
    public DormitoryAdmin selectByUsername(String username) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from dormitory_admin where username='" + username + "'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                return new DormitoryAdmin(id, username, password, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return null;
    }
}
