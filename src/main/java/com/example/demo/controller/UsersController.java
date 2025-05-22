package com.example.demo.controller;
//
//import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.AuthenticationBean;
import com.example.demo.models.Users;
import com.example.demo.service.EmailService;
import com.example.demo.service.OtpService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UsersController {

	private final UserService userserv;
	private final OtpService otpserv;
	private final EmailService emailserv;
	
	public UsersController(UserService userserv, OtpService otpserv, EmailService emailserv) {
		super();
		this.userserv = userserv;
		this.otpserv = otpserv;
		this.emailserv = emailserv;
	}

	@GetMapping("/basicauth")
	public AuthenticationBean authenticationBean()
	{
		return new AuthenticationBean("You are authenticated");
	} 
	
	@GetMapping("/{uname}")
	public ResponseEntity<Users> getUserByUserName(@PathVariable("uname") String uname)
	{
		Users user = userserv.getUserByUserName(uname);
		if(user!=null) {
			return new ResponseEntity<Users>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Users>( HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Users> getUserByUserEmail(@PathVariable("email") String email)
	{
		Users user = userserv.getUserByEmailId(email);
		if(user!=null) {
			return new ResponseEntity<Users>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Users>( HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/otp/{vemail}")
	public ResponseEntity<String> otpForForgotPassword(@PathVariable("vemail") String vemail ,HttpSession sess) {
		
		if(userserv.getUserByEmailId(vemail)!=null) {
			otpserv.generateotp(vemail);
			int otp = otpserv.getOtp(vemail);
			sess.setAttribute("vemail", vemail);
			sess.setAttribute("otp", otp);
			
			emailserv.sendSimpleEmail(vemail, "Respected Sir/Ma'am, \n\t Your OTP to change the password is "+otp, "OTP for confirmation");
			
			return new ResponseEntity<String>(""+otpserv.getOtp(vemail),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/updatepass")
	public ResponseEntity<Users> updatePasswordUsingEmail(@RequestBody Users users)
	{
		Users user = userserv.getUserByEmailId(users.getEmail());
		int res = userserv.updateUsersPassword(users.getCnf_pass(), user.getUser_id());
		if(res>0)
			return new ResponseEntity<Users>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
}