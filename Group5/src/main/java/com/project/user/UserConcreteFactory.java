package com.project.user;

public class UserConcreteFactory extends UserAbstractFactory {

	@Override
	public IUser createUser() {
		return new User();
	}

	@Override
	public IUserDAO createUserDAO() {
		return new UserDAO();
	}

	
}
