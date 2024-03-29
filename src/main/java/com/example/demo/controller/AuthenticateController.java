package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtils;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.services.Implementaion.UserDetailsImplementation;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsImplementation userDetailservice;

	@Autowired
	private JwtUtils jwtUtils;

	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//authenticate

		UserDetails userDetails = this.userDetailservice.loadUserByUsername(jwtRequest.getUsername());
		String token=jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));

	}

	private void authenticate(String username,String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(DisabledException e) {
			throw new Exception("USER DISABLED"+e.getMessage());
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials "+e.getMessage());
		}
	}

	//returns details of current user
	@GetMapping("/current-user")
	//The principal is the currently logged in user. 
	public User getCurrentUser(Principal principal) {
		UserDetails user = this.userDetailservice.loadUserByUsername(principal.getName());
		return (User)user;
	}
	
	
}
