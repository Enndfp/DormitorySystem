package com.enndfp.dao.impl;

import com.enndfp.dao.BuildingDao;
import com.enndfp.entity.Building;
import com.enndfp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDaoImpl implements BuildingDao {
    @Override
    public List<Building> selectAll() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT b.id,b.`name`,b.introduction,d.`name` adminName,d.id adminId FROM building b,dormitory_admin d WHERE b.admin_id=d.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Building> buildingList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String introduction = resultSet.getString("introduction");
                String adminName = resultSet.getString("adminName");
                Integer adminId = resultSet.getInt("adminId");
                buildingList.add(new Building(id,name,introduction,adminId,adminName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return buildingList;
    }

    @Override
    public List<Building> select(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT b.id,b.`name`,b.introduction,d.`name` adminName,d.id adminId FROM building b,dormitory_admin d WHERE b.admin_id=d.id AND b."+key+" LIKE '%"+value+"%';";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Building> buildingList=new ArrayList<>();
        try {
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String introduction = resultSet.getString("introduction");
                String adminName = resultSet.getString("adminName");
                Integer adminId = resultSet.getInt("adminId");
                buildingList.add(new Building(id,name,introduction,adminId,adminName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return buildingList;
    }

    @Override
    public Integer save(Building building) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into building(name,introduction,admin_id) values(?,?,?) ";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, building.getName());
            statement.setString(2, building.getIntroduction());
            statement.setInt(3, building.getAdminId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public Integer update(Building building) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update building set name=?,introduction=?,admin_id=? where id=?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, building.getName());
            statement.setString(2, building.getIntroduction());
            statement.setInt(3, building.getAdminId());
            statement.setInt(4, building.getId());
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
        String sql = "delete from building where id="+id;
        PreparedStatement statement = null;
        Integer result=null;
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
}
