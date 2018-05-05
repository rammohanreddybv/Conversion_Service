package com.needa.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.needa.org.entity.FileDetails;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails, Integer> {

	List<FileDetails> findByuserName(String userName);
}
