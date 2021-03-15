package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
		Connection connection = this.dbUtilities.estConnection();
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
		} finally {
			dbUtilities.closeConnection(connection);
		}
		System.out.println(userfromDB);
		return userfromDB;
	}
	
	
	@Override
	public void saveUser(User user) {
		Connection conn = dbUtilities.estConnection();
		if(user.getId() == 0) {
			System.out.println("add new user");
			try {
				BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
		        String encodedpassword = encoder.encode(user.getPassword());
				CallableStatement stmt = conn.prepareCall("{call addUser( ? , ? , ? , ?, ?, ?, ?)}");
				stmt.setString(1, user.getFirstName());
				stmt.setString(2, user.getLastName());
				stmt.setString(3, user.getGender());
				stmt.setDate(4, user.getDateOfBirth());
				stmt.setInt(5, user.getMobileNumber());
				stmt.setString(6, user.getUserName());
				stmt.setString(7, encodedpassword);

				stmt.execute();
				System.out.println("Data entered");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
