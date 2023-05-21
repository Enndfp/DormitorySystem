package com.enndfp.dao.impl;

import com.enndfp.dao.SystemAdminDao;
import com.enndfp.entity.SystemAdmin;
import com.enndfp.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAdminDaoImpl implements SystemAdminDao {
    @Override
    public SystemAdmin selectByUsername(String username) {
        Connection connection = JDBCUtil.getConnection();
        String sql = "select * from system_admin where username='" + username + "'";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String telephone = resultSet.getString("telephone");
                return new SystemAdmin(id, username, password, name, telephone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.getClose(connection, statement, resultSet);
        }
        return null;
    }
}
