package com.enndfp.dao.impl;

import com.enndfp.dao.DormitoryDao;
import com.enndfp.entity.Dormitory;
import com.enndfp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryDaoImpl implements DormitoryDao {
    @Override
    public List<Dormitory> selectAll() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT d.id,b.`name` buildingName,d.`name` dormitoryName,d.type,d.available,d.telephone FROM dormitory d,building b WHERE d.building_id=b.id";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dormitory> dormitoryList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String buildingName = resultSet.getString("buildingName");
                String dormitoryName = resultSet.getString("dormitoryName");
                Integer type = resultSet.getInt("type");
                Integer available = resultSet.getInt("available");
                String telephone = resultSet.getString("telephone");
                dormitoryList.add(new Dormitory(id, buildingName, dormitoryName, type, available, telephone));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return dormitoryList;
    }

    @Override
    public List<Dormitory> select(String key, String value) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "SELECT d.id,b.`name` buildingName,d.`name` dormitoryName,d.type,d.available,d.telephone FROM dormitory d,building b WHERE d.building_id=b.id AND d." + key + " LIKE '%" + value + "%'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dormitory> dormitoryList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String buildingName = resultSet.getString("buildingName");
                String dormitoryName = resultSet.getString("dormitoryName");
                Integer type = resultSet.getInt("type");
                Integer available = resultSet.getInt("available");
                String telephone = resultSet.getString("telephone");
                dormitoryList.add(new Dormitory(id, buildingName, dormitoryName, type, available, telephone));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return dormitoryList;
    }

    @Override
    public Integer save(Dormitory dormitory) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into dormitory(building_id,name,type,available,telephone) values(?,?,?,?,?) ";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dormitory.getBuildingId());
            statement.setString(2, dormitory.getName());
            statement.setInt(3, dormitory.getType());
            statement.setInt(4, dormitory.getAvailable());
            statement.setString(5, dormitory.getTelephone());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public Integer update(Dormitory dormitory) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update dormitory set name=?,telephone=? where id=?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitory.getName());
            statement.setString(2, dormitory.getTelephone());
            statement.setInt(3, dormitory.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, null);
        }
        return result;
    }

    @Override
    public List<Dormitory> availableList() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from dormitory where available>0";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dormitory> dormitoryList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer buildingId = resultSet.getInt("building_id");
                String name = resultSet.getString("name");
                Integer type = resultSet.getInt("type");
                Integer available = resultSet.getInt("available");
                String telephone = resultSet.getString("telephone");
                dormitoryList.add(new Dormitory(id, buildingId, name, type, available, telephone));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return dormitoryList;
    }

    @Override
    public Integer updateAvailable(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update dormitory set available=available-1 where id=" + id;
        PreparedStatement statement = null;
        Integer result = null;
        List<Dormitory> dormitoryList = new ArrayList<>();
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
    public Integer addAvailable(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update dormitory set available=available+1 where id=" + id;
        PreparedStatement statement = null;
        Integer result = null;
        List<Dormitory> dormitoryList = new ArrayList<>();
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
    public List<Integer> findDormitoryIdByBuildingId(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id from dormitory where building_id=" + id;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> dormitoryIdList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dormitoryIdList.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return dormitoryIdList;
    }

    @Override
    public Integer availableId() {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id from dormitory where available>0 limit 0,1";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer result = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return result;
    }

    @Override
    public Integer deleteById(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "delete from dormitory where id=" + id;
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
    public List<Dormitory> findByBuildingId(Integer id) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select id,name from dormitory where building_id=" + id;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Dormitory> dormitoryList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                dormitoryList.add(new Dormitory(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return dormitoryList;
    }

}
