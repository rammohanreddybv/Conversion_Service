package com.needa.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.needa.org.entity.FileDetails;
import com.needa.org.repository.FileDetailsRepository;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	
	FileDetailsRepository fileDetailsRepository;
	@Override
	public void createFileDetails(FileDetails filedetails) {
		fileDetailsRepository.save(filedetails);
		
	}

	@Override
	public void updateFileDetails(FileDetails filedetails, int id) {
		
		
		
	}

	@Override
	public void deleteFileDetails(int id) {
		// TODO Auto-generated method stub
		fileDetailsRepository.deleteById(id);
		
	}

	@Override
	public List<FileDetails> getAllFileDetails() {
		return fileDetailsRepository.findAll();
	}

	@Override
	public FileDetails getOneFile(int id) {
		return fileDetailsRepository.getOne(id);
	}

	@Override
	public List<FileDetails> findByuserName(String userName) {
		return fileDetailsRepository.findByuserName(userName);
	}

}
