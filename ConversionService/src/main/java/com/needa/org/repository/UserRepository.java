package com.needa.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.needa.org.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	User findByUserName(String userName);
	User findByConfirmationToken(String confirmationToken);
	

}
