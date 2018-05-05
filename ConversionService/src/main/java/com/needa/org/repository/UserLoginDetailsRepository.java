package com.needa.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.needa.org.entity.UserLoginDetails;

@Repository
public interface UserLoginDetailsRepository extends JpaRepository<UserLoginDetails, Integer> {
	
	UserLoginDetails findByPasswordToken(String passwordToken);
	UserLoginDetails findByUserName(String userName);

}
