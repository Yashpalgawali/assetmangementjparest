package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@CrossOrigin("*")
@RequestMapping("users")
public class UsersController {

	@Autowired
	UserService userserv;
	
	@Autowired
	OtpService otpserv;
	
	@Autowired
	EmailService emailserv;
	
	@GetMapping("/basicauth")
	public AuthenticationBean authenticationBean()
	{
		System.err.println("Inside Basic Authentication user Controller\n");
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
	{System.err.println("inside updatePasswordUsingEmail()");
		Users user = userserv.getUserByEmailId(users.getEmail());
		System.err.println("User ID = "+users.getUser_id()+"\n User email = "+user.getEmail()+"\n New Password = "+users.getCnf_pass());
		int res = userserv.updateUsersPassword(users.getCnf_pass(), user.getUser_id());
		System.err.println("Update result from DB is "+res);
		return new ResponseEntity<Users>(HttpStatus.OK);
	}
	

//	@RequestMapping("/forgotpassword")
//	public String forGotPassword(@ModelAttribute("Users") Users users,HttpSession sess ,RedirectAttributes attr) {
//		
//		Users user = userserv.getUserByEmailId(users.getUser_email());
//		
//		if(user!=null) 	{
//			otpserv.generateotp(users.getUser_email());
//			int otp = otpserv.getOtp(users.getUser_email());
//			sess.setAttribute("vemail", users.getUser_email());
//			sess.setAttribute("otp", otp);
//			sess.setAttribute("userid", user.getUser_id());
//			emailserv.sendSimpleEmail(users.getUser_email(), "Respected Sir/Ma'am, \n\t Your OTP for change password is "+otp, "OTP for confirmation");
//			attr.addFlashAttribute("response","OTP sent to your email "+users.getUser_email());
//			return "redirect:/confotppass";
//		}
//		else{
//			attr.addFlashAttribute("reserr", "User Not found for Given Email");
//			return "redirect:/forgotpass";
//		}
//	}
	
//	@GetMapping("/confotppass")
//	public String confOTPForgotPassword(@ModelAttribute("Users") Users users,Model model,HttpSession sess) {
//		model.addAttribute("vemail", sess.getAttribute("vemail"));
//		return "ConfirmOtpForgotPass";
//	}
//	
//	@PostMapping("/confotppassword")
//	public String confirmOtpPassword(@ModelAttribute("Users") Users users, 
//									HttpSession sess,RedirectAttributes attr) {
//		Integer n_otp = Integer.parseInt(users.getCnf_otp());
//		int  new_otp  = n_otp;
//		Integer o_otp = (Integer) sess.getAttribute("otp");;
//		int  old_otp  = o_otp;
//		
//		if(new_otp==old_otp) {
//			Users user = userserv.getUserByEmailId(users.getUser_email());
//			otpserv.clearOtp((String)sess.getAttribute("vemail"));
//			sess.setAttribute("userid", user.getUser_id());
//			return "redirect:/changepass";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "OTP did not matched");
//			return "redirect:/confotppass";
//		}
//	}
	
}