package com.example.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.Implementaion.UserDetailsImplementation;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsImplementation userDEtailservice;
	
	@Autowired
	private JwtUtils jwtUtil;
	//validate token , check expiration
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String header = request.getHeader("Authorization");
		System.out.println(header);
		String username=null;
		String jwtToken=null;
		
		//herer Bearer is imp
		if(header!=null && header.startsWith("Bearer ")) {
			//yes
			
			jwtToken = header.substring(7);
			
			try {
				
			}catch(ExpiredJwtException e)
			{
				e.printStackTrace();
				System.out.println("jwt token is expired");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			username = this.jwtUtil.extractUsername(jwtToken);
			
		}
		else {
			System.out.println("Invalid Token , not start with bearer ");
		}
		
		//validete
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userdetails = this.userDEtailservice.loadUserByUsername(username);
			if(this.jwtUtil.validateToken(jwtToken, userdetails)) {
				UsernamePasswordAuthenticationToken upa=new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
				upa.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upa);
			}
		}
		else {
			System.out.println("Token is not valid");
		}
		
		filterChain.doFilter(request, response);
	}
	
	
}
