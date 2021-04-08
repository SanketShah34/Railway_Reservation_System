package com.project.user;

public abstract class UserAbstractFactory {
	private static UserAbstractFactory instance = null;
	
	public abstract IUser createUser();
	public abstract IUserDAO createUserDAO();
	
	public static UserAbstractFactory instance() {
		if (null == instance) {
			instance = new UserConcreteFactory();
		}
		return instance;
	}
}
