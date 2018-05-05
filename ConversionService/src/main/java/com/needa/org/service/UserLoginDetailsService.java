package com.needa.org.service;

import org.springframework.stereotype.Service;

import com.needa.org.entity.UserLoginDetails;

@Service
public interface UserLoginDetailsService {

	public UserLoginDetails findByPasswordToken(String passwordToken);
	public UserLoginDetails createUserLoginDetails(UserLoginDetails userLoginDetails);
	public UserLoginDetails updateUserLoginDetails(UserLoginDetails userLoginDetails);
	public UserLoginDetails findByUserName(String userName);
}
