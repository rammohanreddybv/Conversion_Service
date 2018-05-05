package com.needa.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.needa.org.dao.UserDao;
import com.needa.org.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(User user) {
		System.out.print("saving user to database");
		return userDao.createUser(user);

	}

	@Override
	public void updateUser(User user, int userId) {
		// TODO Auto-generated method stub
		userDao.updateUser(user, userId);

	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}

	@Override
	public User findByConfirmationToken(String confirmationToken) {
		// TODO Auto-generated method stub
		return userDao.findByConfirmationToken(confirmationToken);
	}

	@Override
	public String userLoing(User user) {
		return userDao.userLogin(user);
		
	}

}
