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
import com.project.database.IDatabaseUtilities;
import com.project.security.SecurityAbstractFactory;


@Component
@ComponentScan("com.project.service")
@ComponentScan("com.code.database")
public class UserDAO implements IUserDAO {
	
	@Override
	public IUser getUserByUsername(String username) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IUser userfromDB = userAbstractFactory.createUser();
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
     		connection = databaseUtilities.establishConnection();
     		statement = connection.prepareCall("{call findUserByUserName(? )}");
     		statement.setString(1, username);
			boolean hadResults = statement.execute();
			if (hadResults) {
				 resultSet = statement.getResultSet();
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
			e.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return userfromDB;
	}
	
	
	@Override
	public void saveUser(IUser user) {
		
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		
		if(user.getId() == 0) {
			try {
				BCryptPasswordEncoder encoder  = securityAbstractFactory.createPasswordEncoder();
		        String encodedpassword = encoder.encode(user.getPassword());
		        statement = connection.prepareCall("{call addUser( ? , ? , ? , ?, ?, ?, ?)}");
		        statement.setString(1, user.getFirstName());
		        statement.setString(2, user.getLastName());
		        statement.setString(3, user.getGender());
		        statement.setDate(4, (Date)user.getDateOfBirth());
		        statement.setInt(5, user.getMobileNumber());
		        statement.setString(6, user.getUserName());
		        statement.setString(7, encodedpassword);

		        statement.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				databaseUtilities.closeStatement(statement);
				databaseUtilities.closeConnection(connection);
			}
		}
	}

}
