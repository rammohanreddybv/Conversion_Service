package com.needa.org.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.needa.org.entity.FileDetails;
import com.needa.org.entity.User;
import com.needa.org.entity.UserLoginDetails;
import com.needa.org.service.EmailService;
import com.needa.org.service.FileUploadService;
import com.needa.org.service.UserLoginDetailsService;
import com.needa.org.service.UserService;
import com.needa.org.userAuthentication.TokenMap;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	EmailService emailService;
	@Autowired
	UserLoginDetailsService userLoginDetailsService;
	@Autowired
	FileUploadService fileUploadService;
	
	@Value("${base.url}")
	String baseUrl;

	@Autowired
	TokenMap tokenMap;

	String SECERT="user1234";

	@GetMapping("/all")
	public List<User> getAllUsers(){

		return userService.getAllUsers();


	}

	@PostMapping(value="/register")	
	public String createUser(@RequestBody User user,HttpServletRequest servletRequest) {

		System.out.println(user.getUserName());
		User user1=userService.findByUserName(user.getUserName());
		System.out.println(user1);

		System.out.println(servletRequest.getSession().getId());
		if(user1!=null) {
			return "UserName is already existed";
		}
		else {

			user.setEnabled(false);

			String confirmationToken=UUID.randomUUID().toString();
			user.setConfirmationToken(confirmationToken);
			userService.createUser(user);



			//String urlPath="http://localhost:8080";
			String urlPath = baseUrl;


			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ urlPath + "/confirm/" + user.getConfirmationToken()+"\n"+ " or please enter the confirmation token : " + user.getConfirmationToken());
			//registrationEmail.setFrom("rammohanreddy.b.v@gmail.com");

			emailService.sendEmail(registrationEmail);


			return "Registered Successfully";

		}
	}

	@GetMapping("/confirm/{confirmation-token}")
	public String confirmUser(@PathVariable("confirmation-token") String confirmationToken ) {

		User user=userService.findByConfirmationToken(confirmationToken);
		if(user!=null) {

			user.setEnabled(true);
			userService.updateUser(user, user.getUserId());
			return "your account is activated";
		}
		else {
			return "your account is not yet active";
		}
	}

	@PostMapping("/confirm")
	public String confirmUserbyPost(@RequestBody User user ) {


		User user1=userService.findByConfirmationToken(user.getConfirmationToken());

		if(user1!=null) {

			user1.setEnabled(true);
			userService.updateUser(user1, user1.getUserId());
			return "your account is activated";
		}
		else {
			return "your account is not yet active";
		}
	}

	@PostMapping("/forgotpassword")
	public String forgotPassword(@RequestBody UserLoginDetails userLoginDetails) {

		String userName=userLoginDetails.getUserName();

		User user=userService.findByUserName(userName);

		if(user==null) {
			return "The user is not exist username, Please provide correct username";
		}
		else {
			String passwordToken=UUID.randomUUID().toString();
			userLoginDetails.setPasswordToken(passwordToken);
			userLoginDetailsService.createUserLoginDetails(userLoginDetails);
			String urlPath=baseUrl;
			SimpleMailMessage forgotPasswordEmail = new SimpleMailMessage();
			forgotPasswordEmail.setTo(user.getEmail());
			forgotPasswordEmail.setSubject("Forgot PasswordToken");
			forgotPasswordEmail.setText(passwordToken+"\nplease click the link below to enter new password:\n"
					+ urlPath + "/forgot" +"\n"+ " or please provide userName, password Token and new password : " );
			//registrationEmail.setFrom("rammohanreddy.b.v@gmail.com");

			emailService.sendEmail(forgotPasswordEmail);

			return "PasswordToken sent to your email";
		}


	}

	@PostMapping("/forgot")
	public String changingPasswordwithToken(@RequestBody UserLoginDetails userLoginDetails) {

		UserLoginDetails userlogin=userLoginDetailsService.findByPasswordToken(userLoginDetails.getPasswordToken());
		if(userlogin!=null) {
			System.out.println(userlogin.getUserName());
			System.out.println(userLoginDetails.getUserName());
			if(userlogin.getUserName().equals(userLoginDetails.getUserName())) {
				userLoginDetails.setId(userlogin.getId());
				userLoginDetailsService.updateUserLoginDetails(userLoginDetails);
				User user=userService.findByUserName(userLoginDetails.getUserName());
				user.setPassword(userLoginDetails.getPassword());
				userService.updateUser(user, user.getUserId());
				return "your password reset completed successfully";
			}
			else {
				return "your user name is invalid";
			}


		}

		return "please enter valid password token";
	}

	@PostMapping("/changepassword")
	public String changePassword(@RequestBody User user,HttpServletRequest req,HttpServletResponse res, @RequestHeader String auth_token)
	{

		//System.out.println("in  change pwd "+user.toString()+" auth "+auth_token);

		String authorizationHeader=req.getHeader("auth_token");

		//System.out.println("auth_token : "+authorizationHeader);

		//String authy = req.getHeader("Authorization");

		//System.out.println("user name : "+user.getUserName()+" AUTH : "+authy);

		Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(SECERT).parseClaimsJws(authorizationHeader);

		String userName = jwsClaims.getBody().getSubject();

		String sessionId = req.getRequestedSessionId();

		Map<String,String> map=tokenMap.getTokenMap();

		String token=map.get(sessionId);


		if(authorizationHeader.equals(token))
		{
			System.out.println("authentication success");
		}

		return null;
	}

	@PostMapping("/login")
	public String userLogin(@RequestHeader String Authorization,HttpServletRequest req,HttpServletResponse res)
	{

		System.out.println("Authy : "+Authorization);
		User user  =  new User();
		String authy[] = Authorization.split(" ");
		if(authy[0].trim().equals("Basic"))
		{
			String autH = authy[1];
			byte[] asdf =  Base64.getDecoder().decode(autH);
			System.out.println(new String(asdf));
			
			String userpwd[] = new String(asdf).trim().split(":");
			
			user.setUserName(userpwd[0]);
			user.setPassword(userpwd[1]);
			
			
		}
		else
		{
			
			System.out.println("Not basic");
			
		}

		System.out.println("User  : "+user.getUserName()+" pwd  "+user.getPassword());
		String message = userService.userLoing(user);
		String SECERT="user1234";
		if(message.equals("success")) {
			long EXPIRATION_TIME=300000;
			String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
			String userName = user.getUserName();
			String token = Jwts.builder().setSubject(userName)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SECERT).compact();
			res.addHeader("auth_token", token);
			Map<String,String> map=new HashMap<String,String>();
			map.put(sessionId, token);
			tokenMap.setTokenMap(map);
			return message;
		}
		else {
			return message;
		}

	}


	@PostMapping("/fileupload")
	public String uploadFile(@RequestParam("file") MultipartFile uploadFile,HttpServletRequest req) throws NoSuchAlgorithmException {
		
		String authorizationHeader=req.getHeader("auth_token");

		

		Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(SECERT).parseClaimsJws(authorizationHeader);

		String userName = jwsClaims.getBody().getSubject();

		String sessionId = req.getRequestedSessionId();

		Map<String,String> map=tokenMap.getTokenMap();

		String token=map.get(sessionId);

		FileDetails fileDetails=new FileDetails();

		if(authorizationHeader.equals(token))
		{
			System.out.println("authentication success");
			
			fileDetails.setUserName(userName);
			if(uploadFile.isEmpty()) {
				return "please upload file";
			}
			else {
				try {
					
					
					byte[] bytes = uploadFile.getBytes();
					
					MessageDigest md=MessageDigest.getInstance("MD5");
					md.update(bytes);
					String checksum=md.digest().toString();
					fileDetails.setWordFileHash(checksum);
					fileDetails.setUploadedDate(new Date());
					fileDetails.setWordFileName(uploadFile.getOriginalFilename());
					List<FileDetails> listoffilesdetails=fileUploadService.getAllFileDetails();
					System.out.println(checksum);
					int flag=0;
					for(FileDetails fd:listoffilesdetails) {
						if(fd.getWordFileHash().equals(checksum)) {
							System.out.println("The file already existed in the system");
							flag=1;
							break;
						}
						
					}
					if(flag==0) {
						File file=new File("uploadedfiles"+"/"+uploadFile.getOriginalFilename());
						if(file.exists()) {
							System.out.println("File is  created");
						}
						Path path = Paths.get("uploadedfiles"+"/"+uploadFile.getOriginalFilename());
						System.out.println(path);
						Files.write(path, bytes);
						fileUploadService.createFileDetails(fileDetails);
					}
					
					

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return  uploadFile.getOriginalFilename()+" will be converted";
		}
		else {
			return "please login to the application";
		}
		
	}

	@GetMapping("/recentFiles")
	public List<String> recentFiles(HttpServletRequest req){

		
		String authorizationHeader=req.getHeader("auth_token");
		Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(SECERT).parseClaimsJws(authorizationHeader);

		String userName = jwsClaims.getBody().getSubject().toString();

		String sessionId = req.getRequestedSessionId();

		Map<String,String> map=tokenMap.getTokenMap();

		String token=map.get(sessionId);

		FileDetails fileDetails=new FileDetails();

		if(authorizationHeader.equals(token))
		{
			System.out.println("authentication success");
			
			List<FileDetails> recentfiledetails=new ArrayList<FileDetails>();
			List<String> recentfile=new LinkedList<>();
			 recentfiledetails=fileUploadService.findByuserName(userName);
			
			 for(FileDetails filedet:recentfiledetails) {
				 recentfile.add(filedet.getWordFileName());
				 
			 }
			return recentfile;
		}
		else {
			System.out.println("Please login to the application");
			return null;
					
		}
		

		
	}

	

}
