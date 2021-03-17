package com.project.user;

public abstract class UserAbstractFactory {
	
	public abstract IUser createUser();
	
	public abstract IUserDAO createUserDAO();

}
