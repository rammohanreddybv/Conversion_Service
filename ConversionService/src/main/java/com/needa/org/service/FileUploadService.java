package com.needa.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.needa.org.entity.FileDetails;

@Service
public interface FileUploadService {

	public void createFileDetails(FileDetails filedetails);
	public void updateFileDetails(FileDetails filedetails,int id);
	public void deleteFileDetails(int id);
	public List<FileDetails> getAllFileDetails();
	public FileDetails getOneFile(int id);
	public List<FileDetails> findByuserName(String userName);
}
