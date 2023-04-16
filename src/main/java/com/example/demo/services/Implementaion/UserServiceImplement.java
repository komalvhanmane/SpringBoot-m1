package com.example.demo.services.Implementaion;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;


@Service
public class UserServiceImplement implements UserService{
	
	//IOC injects dependencies automatically
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	@Override
	
	
	//create
	public User createUser(User user, Set<UserRole> userRoles)throws Exception {
		// TODO Auto-generated method stub
		
		User u=this.userRepo.findByUsername(user.getUsername());
		User u1=this.userRepo.findByEmail(user.getEmail());
		if(u!=null || u1!=null) {
			System.out.println("User is already there");
			throw new Exception("User already present");
		}
		else {
			//saving the roles in database
			for(UserRole userrole:userRoles) {
				roleRepo.save(userrole.getRole());
			}
			// adding useroles to the user
			user.getUserrole().addAll(userRoles);
			
			//sabing the user
			u = this.userRepo.save(user);
		}
		return null;
	}
	
	//user by username
	@Override
	public User getUser(String username) {
		return this.userRepo.findByUsername(username);
	}

	//delete by id
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		this.userRepo.deleteById(id);
	}
}
