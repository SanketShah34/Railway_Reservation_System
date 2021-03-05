package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	AuthenticationSuccessHandler successHandler;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("in web security method 1");
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() { 
		System.out.println("in web security method 2");
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationprovider() {
		System.out.println("in web security method 3");
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		System.out.println("in web security method 4");
		auth.authenticationProvider(authenticationprovider());
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("in web security method 5");
		http.authorizeRequests()
		.antMatchers("/user/home").permitAll()
		.antMatchers("/admin*").hasAnyAuthority("ADMIN")
		.antMatchers("/user*").hasAnyAuthority("USER")
//		.antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
//		.antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//		.antMatchers("/delete/**").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").usernameParameter("userName").passwordParameter("password").successHandler(successHandler)
		.failureUrl("/login-error")
		.permitAll()
		.and().logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/403");

		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
}
