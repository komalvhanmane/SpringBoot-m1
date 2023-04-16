package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.model.exam.Result;
import com.example.demo.services.UserService;

// used to build REST API in a declarative way.
@RestController
//RestController is used for making restful web services 
@RequestMapping("/userr")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	//create ,,  send data to a server to create/update a resource ==>post
	@PostMapping("/s")
	//RequestBody is basically data sent by the client
	public User createUser(@RequestBody User user) throws Exception {
		
		//encoding password with decrypt
		user.setPassword(this.bcrypt.encode(user.getPassword()));
		Set<UserRole> roles=new HashSet<>();
		
		System.out.println("create user");
		Role role=new Role();
		role.setId(2);
		role.setName("Normal");
		
		UserRole us=new UserRole();
		us.setRole(role);
		us.setUser(user);
		
		roles.add(us);
		return this.userService.createUser(user,roles);
	}
	
	
//	@GetMapping("/{username}")
//	//@PathVariable annotation to extract the templated part of the URI
//	public Set<Result> getUser(@PathVariable("username") String username) {
//		System.out.println(this.userService.getUser(username).getResult());
//		
//		return this.userService.getUser(username).getResult();
//	}
	
	//delete by id
	@DeleteMapping("/dele/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		this.userService.deleteUser(id);
	}
	
	
}
