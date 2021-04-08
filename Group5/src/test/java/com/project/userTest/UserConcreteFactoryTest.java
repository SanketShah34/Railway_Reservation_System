package com.project.userTest;

import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.User;
import com.project.user.UserAbstractFactory;
import com.project.user.UserDAO;

public class UserConcreteFactoryTest extends UserAbstractFactoryTest {
	UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
	IUserDAO userDAO = userAbstractFactory.createUserDAO();
	IUser user = userAbstractFactory.createUser();

	@Override
	public IUser createUser() {
		if (null == user) {
			user = new User();
		}
		return user;
	}

	@Override
	public IUserDAO createUserDAO() {
		if (null == userDAO) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}

}
