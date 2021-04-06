package com.project.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.project.security.SecurityAbstractFactory;
import com.project.security.SecurityConcreteFactory;
import com.project.user.IUser;

public abstract class SecurityAbstractFactoryTest 
{
private static SecurityAbstractFactory instance = null;
	
	public abstract UserDetailsService createUserDetailsService();
	
	public abstract BCryptPasswordEncoder createPasswordEncoder();
	
	public abstract DaoAuthenticationProvider createAuthenticationprovider();
	
	public abstract AuthenticationSuccessHandler createCustomeSuccessHandler();
	
	public abstract UserDetails createMyUserDetail(IUser user);
	
	public static SecurityAbstractFactory instance() {
		if (instance == null) {
			instance = new SecurityConcreteFactory();
		}
		return instance;
	}
}
