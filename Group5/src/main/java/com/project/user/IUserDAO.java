package com.project.user;

public interface IUserDAO {

	public IUser getUserByUsername(String username);

	public void saveUser(IUser user);

	public boolean isUserExists(String username);
	
	public boolean isUserPresentWithSameQuestionAndAnswer(IUser user);
	
	public boolean updatePassword(IUser user);

}
