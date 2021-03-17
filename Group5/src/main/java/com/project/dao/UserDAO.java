package com.project.dao;

import com.project.user.User;

public interface UserDAO {
	public  User getUserByUsername(String username); 
	
	public void saveUser(User user); 
}
