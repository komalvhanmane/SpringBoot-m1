package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

	private String authprity;
	
	public Authority (String authprity) {
		this.authprity=authprity;
	}
	
	@Override
	public String getAuthority() {
		
		return this.authprity;
	}
	
}
