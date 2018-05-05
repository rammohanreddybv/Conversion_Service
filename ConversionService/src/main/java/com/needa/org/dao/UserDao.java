package com.needa.org.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.needa.org.entity.User;
import com.needa.org.repository.UserRepository;


@Repository
public class UserDao implements UserRepository {
	
	@Autowired
	private UserRepository userRepository;

	
	@Override
	public List<User> findAll() {		
		return userRepository.findAll();
		
	}

	
	public User getOne(int userId) {
		User user=userRepository.getOne(userId);
		return user;
	}

		
	public User getUser(int userId) {
		
		User user=userRepository.getOne(userId);
		return user;
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
		
	}
	
	public User createUser(User user) {
		System.out.print("saving user to database 2");
		return userRepository.save(user);
	}
	
	public User updateUser(User user,int userId) {
		
		User user1=userRepository.getOne(userId);
		user1.setUserName(user.getUserName());
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());;
		if(user.isEnabled()) {
			user1.setEnabled(true);
		}
		return userRepository.save(user1);
		
	}

	
	public String userLogin(User user) {
		
		User user1=userRepository.findByUserName(user.getUserName());
		if(user1!=null) {
			if(user1.getPassword().equals(user.getPassword())) {
				return "success";
			}
			else {
				return "password is incorrect";
			}
		}
		else {
			return "please enter correct login details";
		}
		
		
	}
	
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
		
	}
	
	@Override
	public User findByConfirmationToken(String confirmationToken) {
		
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<User> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> arg0, Sort arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends User> List<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends User> arg0) {
		// TODO Auto-generated method stub
		
	}


	
	
	@Override
	public <S extends User> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends User> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Optional<S> findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findAllById(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteById(Integer arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean existsById(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Optional<User> findById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
