package com.project.entity;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan
public class User {
	
	public int id;
	
	@NotNull
	@NotEmpty(message = "required field")
	@Size(min=2, max=30 , message = "should be more than 2")
	public String userName;
	
	public String password;
	public boolean enabled;
	public String role;
	
	public User() {
		
	}
	
	public User(int id , String userName , String password , String  role , boolean enabled) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}

}
