# Conversion_Service
Conversion service application for Needa Software Solutions

This conversion is used to convert word files to pdf files

To run this application in local the base URL is http://localhost:8080

create a database conversion_service in mysql

In the application properties set the below properties

spring.mail.username = username
spring.mail.password = password

Actually the mail domain set to gmail, if you want you can change it

First to register the application use the URL http://localhost:8080/register

send the data {
          "userName":"yourname",
          "email":"emailid",
          "password":"password"
}

After registering with your details, you will receive an email with confirmation link and confirmation token, If you want to confirm click the 
link or enter confirmation token as json string in the url http://localhost:8080/confirm

If you forgot the password then click url http://localhost:8080/forgotpassword, then you will receive a token with URL, 
enter userName,password token and new password

For login to the application use the URL http://localhost:8080/login

I used basic auth for this, In the postman go to authorization option select basic auth, give your username and password for application
once you logged in to the application you will recieve an authorization token in the response header of url http://localhost:8080/login
copy the token and paste in the url when ever you want hit fileupload and recent Files

For uploading file use the url http://localhost:8080/fileupload, add token in the headers with auth_token as key and value is token which 
got in the login url and upload the file in the body select form-data option key as file and in the place value enter file

whenever you click http://localhost:8080/recentFiles you will get your recent files


