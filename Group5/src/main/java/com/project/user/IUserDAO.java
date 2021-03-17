package com.project.user;

public interface IUserDAO {

	public IUser getUserByUsername(String username);

	public void saveUser(IUser user);

}
