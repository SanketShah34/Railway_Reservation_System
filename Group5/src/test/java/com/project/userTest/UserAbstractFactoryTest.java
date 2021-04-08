package com.project.userTest;

import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;
import com.project.user.UserConcreteFactory;

public abstract class UserAbstractFactoryTest {
	private static UserAbstractFactory instance = null;

	public static UserAbstractFactory instance() {
		if (null == instance) {
			instance = new UserConcreteFactory();
		}
		return instance;
	}

	public abstract IUser createUser();

	public abstract IUserDAO createUserDAO();

}
