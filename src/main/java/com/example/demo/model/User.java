package com.example.demo.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.exam.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;

//creates entity in database
@Entity
@Table(name="users")
//new class is also okay for userDetaikls
public class User implements UserDetails{
	
	//id is basically primary key
	@Id
	//value automatically generated
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fname;
	private String lname;
	private String username;
	private String password;
	private String phone;
	private String email;
	
	//cascadetype all allows all operations
	//FetchType.EAGER – Fetch it so you’ll have it when you need it
	//user will be having roles and that will be unique and many
	@OneToMany(cascade = CascadeType.ALL,fetch= FetchType.EAGER,mappedBy = "user")
	//mappedBy property is what we use to tell Hibernate which variable we are using to represent the parent class in our child class.
	@JsonIgnore
	//sometimes circular dependencies creates so to avoid that ignorejson
	private Set<UserRole> userrole=new HashSet<>();
	
	public Set<UserRole> getUserrole() {
		return userrole;
	}

	public void setUserrole(Set<UserRole> userrole) {
		this.userrole = userrole;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String fname, String lname, String username, String password, String phone, String email,
			boolean enable,Set<UserRole> userrole) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.enable = enable;
		this.userrole=new HashSet<>();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", username=" + username + ", password="
				+ password + ", phone=" + phone + ", email=" + email + "userrole=" + userrole
				+ ", enable=" + enable + "]";
	}

	private boolean enable=true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	
	//userdetails method
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Authority> set=new HashSet<>();
		
		
		this.userrole.forEach(u ->
		{///here get name means userrole name
			//mnje admin asel tr admin kiva normal store hoyil
			set.add(new Authority(u.getRole().getName()));
		});
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
