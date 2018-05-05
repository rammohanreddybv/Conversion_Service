package com.needa.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.needa.org.entity.User;

@Service
public interface UserService {
	
	public List<User> getAllUsers();
	public User createUser(User user);
	public void updateUser(User user,int userId);
	public User getUser(int userId);
	public void deleteUser(int userId);
	public String userLoing(User user);
	public User findByUserName(String userName);
	public User findByConfirmationToken(String confirmationToken);

	
	

}
