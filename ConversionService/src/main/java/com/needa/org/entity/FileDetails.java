package com.needa.org.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "filedetails")
public class FileDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String userName;

	@Column(name = "wordfilename")
	private String wordFileName;

	@Column(name = "wordfilehash")
	private String wordFileHash;

	@Column(name = "pdffilename")
	private String pdfFileName;

	@Column(name = "pdffilehash")
	private String pdfFileHash;

	@Column(name = "uploadeddate")
	private Date uploadedDate;

	public FileDetails() {

	}

	public FileDetails(int id, String userName, String wordFileName, String wordFileHash, String pdfFileName,
			String pdfFileHash, Date uploadedDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.wordFileName = wordFileName;
		this.wordFileHash = wordFileHash;
		this.pdfFileName = pdfFileName;
		this.pdfFileHash = pdfFileHash;
		this.uploadedDate = uploadedDate;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWordFileName() {
		return wordFileName;
	}

	public void setWordFileName(String wordFileName) {
		this.wordFileName = wordFileName;
	}

	public String getWordFileHash() {
		return wordFileHash;
	}

	public void setWordFileHash(String wordFileHash) {
		this.wordFileHash = wordFileHash;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public String getPdfFileHash() {
		return pdfFileHash;
	}

	public void setPdfFileHash(String pdfFileHash) {
		this.pdfFileHash = pdfFileHash;
	}

	
	
}
