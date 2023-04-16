package com.example.demo.services.Implementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

//It is used to mark the class as a service provider
@Service
public class UserDetailsImplementation implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	//loading data by username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User u = userRepo.findByUsername(username);
		if(u == null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("User Not Found Exception");
		}
		
		return u;
	}
	
}
