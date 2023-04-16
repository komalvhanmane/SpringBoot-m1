package com.example.demo.services;

import java.util.Set;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;

public interface UserService {
	
	//crerate here 1 user can have many roles
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//user by usernmae
	public User getUser(String username);
	
	//delete user id
	public void deleteUser(int id);

}
