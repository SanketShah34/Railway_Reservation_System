package com.project.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.project.user.IUser;


public class SecurityConcreteFactory extends SecurityAbstractFactory {

	@Override
	public UserDetailsService createUserDetailsService()
	{
		 return new UserDetailsServiceImpl();
	}

	@Override
	public BCryptPasswordEncoder createPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Override
	public DaoAuthenticationProvider createAuthenticationprovider()
	{
		return new DaoAuthenticationProvider();
	}

	@Override
	public AuthenticationSuccessHandler createCustomeSuccessHandler()
	{
		return new CustomSuccessHandler();  
	}

	@Override
	public UserDetails createMyUserDetail(IUser user)
	{
		return new MyUserDetails(user);
	}

}
