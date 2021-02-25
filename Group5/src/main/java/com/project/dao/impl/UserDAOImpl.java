package com.project.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.project.dao.UserDAO;
import com.project.entity.User;
import com.project.service.DButilities;

@Component
@ComponentScan("com.project.service")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private DButilities dbUtilities;

	@Override
	public User getUserByUsername(String username) {
		System.out.println("in user dao imple ");
		User userfromDB = new User();
		Connection connection = this.dbUtilities.EstConnection();
		try {
			java.sql.CallableStatement stmt = connection.prepareCall("{call findUserByUserName(? )}");
			stmt.setString(1, username);
			boolean hadResults = stmt.execute();
			if (hadResults) {
				ResultSet resultSet = stmt.getResultSet();
				if (resultSet.next()) {
					int id = resultSet.getInt("id");
					String userName = resultSet.getString("userName");
					String password = resultSet.getString("password");
					boolean enabled = resultSet.getBoolean("enabled");
					String role = resultSet.getString("role");
					userfromDB.setId(id);
					userfromDB.setUserName(userName);
					userfromDB.setPassword(password);
					userfromDB.setEnabled(enabled);
					userfromDB.setRole(role);
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userfromDB);
		return userfromDB;
	}
}
