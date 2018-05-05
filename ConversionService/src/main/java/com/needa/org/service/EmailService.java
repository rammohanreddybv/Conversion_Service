package com.needa.org.service;



	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.mail.SimpleMailMessage;
	import org.springframework.mail.javamail.JavaMailSender;
	import org.springframework.scheduling.annotation.Async;
	import org.springframework.stereotype.Service;

	@Service
	public class EmailService {

	  @Autowired
	  private JavaMailSender mailSender;

	 
	  public EmailService(JavaMailSender mailSender) {
	    this.mailSender = mailSender;
	  }
	  
	  @Async
	  public void sendEmail(SimpleMailMessage email) {
	    mailSender.send(email);
	  }
	}
