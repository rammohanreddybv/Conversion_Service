package com.needa.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.needa.org.entity.UserLoginDetails;
import com.needa.org.repository.UserLoginDetailsRepository;

@Service
public class UserLoginDetailsServiceImpl implements UserLoginDetailsService {
	
	@Autowired
	private UserLoginDetailsRepository userLoginDetailsRepository;
	
	@Override
	public UserLoginDetails createUserLoginDetails(UserLoginDetails userLoginDetails) {
		return userLoginDetailsRepository.save(userLoginDetails);
	}
	
	@Override
	public UserLoginDetails findByPasswordToken(String passwordToken) {
		return userLoginDetailsRepository.findByPasswordToken(passwordToken);
	}

	@Override
	public UserLoginDetails updateUserLoginDetails(UserLoginDetails userLoginDetails) {
		
		return userLoginDetailsRepository.save(userLoginDetails);
	}

	@Override
	public UserLoginDetails findByUserName(String userName) {
		return userLoginDetailsRepository.findByUserName(userName);
	}


}
