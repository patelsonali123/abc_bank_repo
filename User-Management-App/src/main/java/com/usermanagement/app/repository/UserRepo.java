package com.usermanagement.app.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.app.entity.User;

public interface UserRepo extends JpaRepository<User, Serializable>{
   //select* from user where email=?
	public User findByEmail(String email);
	
	public User finByEmailandUserPwd(String email, String pwd);
}
