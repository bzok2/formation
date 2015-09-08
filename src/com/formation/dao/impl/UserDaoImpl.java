package com.formation.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.formation.dao.UserDao;
import com.formation.dao.utils.DaoUtils;
import com.formation.entity.User;

public class UserDaoImpl implements UserDao {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/formation";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	static {
		// Driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException("Can not load Driver", e);
		}
	}

	@Override
	public List<User> getAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.createStatement();
			String sql = "SELECT * FROM user";
			resultSet = statement.executeQuery(sql);
			List<User> userList = new ArrayList<User>();
			while (resultSet.next()) {
				User user = User.builder()
						.setId(resultSet.getLong("id"))
						.setLogin(resultSet.getString("login"))
						.setPassword(resultSet.getString("password"))
						.build();
				userList.add(user);
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DaoUtils.closeAll(resultSet, statement, connection);
		}
		return null;
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
	}

}
