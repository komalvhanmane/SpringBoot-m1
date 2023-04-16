package com.example.demo;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.services.UserService;

@SpringBootApplication
public class ExamPortalBackendApplication implements CommandLineRunner{

//	You cannot @Autowired static fields,
	@Autowired
	private UserService userserv;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ExamPortalBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("HEllo");
//		User u=new User();
//		u.setFname("Komal");
//		u.setLname("Vhanmane");
//		u.setEmail("vhanmanekomal120603@gmail.com");
//		u.setPhone("9156150411");
//		u.setPassword(this.bcrypt.encode("Komal@123"));
//		u.setUsername("komal.vhanmane_12");
//		
//		Role role=new Role();
//		role.setId(1);
//		role.setName("Admin");
//		
//		Set<UserRole> userRoleSet =new HashSet<>();
//		UserRole usrreo=new UserRole();
//		usrreo.setRole(role);
//		usrreo.setUser(u);
//		userRoleSet.add(usrreo);
////		System.out.println(userserv);
//		User u1=userserv.createUser(u, userRoleSet);
//		System.out.println(u1.getFname()+" "+u1.getLname()+" "+u1.getEmail()+" "+u1.getPassword()+" "+u1.getUsername()+" "+u1.getPhone());
	}

}
