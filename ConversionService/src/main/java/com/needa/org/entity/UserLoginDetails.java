package com.needa.org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userlogindetails")
public class UserLoginDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="userName",nullable=false,unique=true)
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="passwordtoken")
	private String passwordToken;
	
	
	public UserLoginDetails() {
		
	}

	public UserLoginDetails(int id, String userName, String password, String passwordToken) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.passwordToken = passwordToken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordToken() {
		return passwordToken;
	}

	public void setPasswordToken(String passwordToken) {
		this.passwordToken = passwordToken;
	}
	
	
	

}
