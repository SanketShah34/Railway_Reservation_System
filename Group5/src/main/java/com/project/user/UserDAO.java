package com.project.user;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.DatabaseConcreteFactory;
import com.project.database.IDatabaseUtilities;
import com.project.security.SecurityAbstractFactory;
import com.project.security.SecurityConcreteFactory;

@Component
@ComponentScan("com.project.service")
@ComponentScan("com.code.database")
public class UserDAO implements IUserDAO {
	
	

	@Override
	public IUser getUserByUsername(String username) {
		System.out.println("in user dao imple ");
		IUser userfromDB = new User();
		Connection connection = null;
		DatabaseAbstactFactory databaseAbstractFactory = new DatabaseConcreteFactory();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		
		try {
			
			
     		connection = databaseUtilities.establishConnection();
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
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			databaseUtilities.closeConnection(connection);
		}
		System.out.println(userfromDB);
		return userfromDB;
	}
	
	
	@Override
	public void saveUser(IUser user) {
		
		DatabaseAbstactFactory databaseAbstractFactory = new DatabaseConcreteFactory();
		SecurityAbstractFactory securityAbstractFactory = new SecurityConcreteFactory();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();	
		Connection connection = databaseUtilities.establishConnection();
		
		if(user.getId() == 0) {
			System.out.println("add new user");
			try {
				BCryptPasswordEncoder encoder  = securityAbstractFactory.createPasswordEncoder();
		        String encodedpassword = encoder.encode(user.getPassword());
				CallableStatement stmt = connection.prepareCall("{call addUser( ? , ? , ? , ?, ?, ?, ?)}");
				stmt.setString(1, user.getFirstName());
				stmt.setString(2, user.getLastName());
				stmt.setString(3, user.getGender());
				stmt.setDate(4, (Date)user.getDateOfBirth());
				stmt.setInt(5, user.getMobileNumber());
				stmt.setString(6, user.getUserName());
				stmt.setString(7, encodedpassword);

				stmt.execute();
				System.out.println("Data entered");

			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				databaseUtilities.closeConnection(connection);
			}
		}
		
	}



}
