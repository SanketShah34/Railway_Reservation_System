package com.project.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;
import com.project.user.UserConcreteFactory;

public class UserDetailsServiceImpl  implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAbstractFactory userAbstractFactory = new UserConcreteFactory();
		IUserDAO userDAO = userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();
		user = userDAO.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Could not find User");
		}
		return new MyUserDetails(user);
	}
}
